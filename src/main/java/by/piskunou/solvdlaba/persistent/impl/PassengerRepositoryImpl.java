package by.piskunou.solvdlaba.persistent.impl;

import by.piskunou.solvdlaba.domain.Passenger;
import by.piskunou.solvdlaba.persistent.PassengerRepository;
import by.piskunou.solvdlaba.DataSourceConfig;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
@RequiredArgsConstructor
public class PassengerRepositoryImpl implements PassengerRepository {

    private final DataSourceConfig config;

    private static final String CREATE = """
            insert into passenger(fk_country_id, firstname, surname, date_of_birth, age, gender)
            values (?, ?, ?, ?, ?, ?)""";

    private static final String EXISTS_BY_ID = "select exists (select from passenger where id= ?)";

    @Override
    @SneakyThrows
    public void create(Passenger passenger) {
        Connection conn = config.getConnection();

        try(PreparedStatement preparedStatement =
                    conn.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setLong(1, passenger.getCountry().getId());
            preparedStatement.setString(2, passenger.getFirstname());
            preparedStatement.setString(3, passenger.getSurname());
            preparedStatement.setDate(4, Date.valueOf(passenger.getDateOfBirth()));
            preparedStatement.setString(5, passenger.getAge().toString());
            preparedStatement.setString(6, passenger.getGender().toString());
            preparedStatement.executeUpdate();

            try(ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if(resultSet.next()) {
                    Long id = resultSet.getLong("id");

                    passenger.setId(id);
                }
            }
        }
    }

    @Override
    @SneakyThrows
    public boolean isExists(long id) {
        Connection conn = config.getConnection();

        try (PreparedStatement preparedStatement = conn.prepareStatement(EXISTS_BY_ID)) {
            preparedStatement.setLong(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return resultSet.getBoolean("exists");
            }
        }
    }

}
