package by.piskunou.solvdlaba.persistence.jdbc;

import by.piskunou.solvdlaba.domain.Airline;
import by.piskunou.solvdlaba.persistence.AirlineRepository;
import by.piskunou.solvdlaba.persistence.DataSourceConfig;
import by.piskunou.solvdlaba.persistence.jdbc.mapper.AirlineMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

//@Repository
@RequiredArgsConstructor
public class AirlineRepositoryImpl implements AirlineRepository {

    private final DataSourceConfig config;
    private final AirlineMapper airlineMapper;

    private final static String FIND_BY_ID = """
            select id as airline_id,
                   name as airline_name
            from airlines where id = ?""";

    private final static String FIND_BY_NAME = """
            select id as airline_id,
                   name as airline_name
            from airlines where name = ?""";

    private final static String FIND_ALL = """
            select id as airline_id,
                   name as airline_name
            from airlines""";

    private static final String CREATE = "insert into airlines(name) values(?)";
    private static final String UPDATE = "update airlines set name = ? where id = ?";
    private static final String DELETE = "delete from airlines where id = ?";
    private static final String EXISTS_BY_ID = "select exists (select from airlines where id = ?)";
    private static final String EXISTS_BY_NAME = "select exists (select from airlines where name = ?)";

    @Override
    @SneakyThrows
    public List<Airline> findAll() {
        Connection conn = config.getConnection();

        try (PreparedStatement preparedStatement = conn.prepareStatement(FIND_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            List<Airline> airlines = new LinkedList<>();

            while (resultSet.next()) {
                Airline airline = airlineMapper.mapRow(resultSet);
                airlines.add(airline);
            }
            return airlines;
        }
    }

    @Override
    @SneakyThrows
    public Optional<Airline> findById(long id) {
        Connection conn = config.getConnection();

        try(PreparedStatement preparedStatement = conn.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                Airline airline = null;
                if(resultSet.next()) {
                    airline = airlineMapper.mapRow(resultSet);
                }
                return Optional.ofNullable(airline);
            }
        }
    }

    @Override
    public Optional<Airline> findByDesignator(String designator) {
        return Optional.empty();
    }

    @Override
    @SneakyThrows
    public void create(Airline airline) {
        Connection conn = config.getConnection();

        try(PreparedStatement preparedStatement =
                    conn.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, airline.getName());
            preparedStatement.executeUpdate();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if(resultSet.next()) {
                    Long id = resultSet.getLong("id");

                    airline.setId(id);
                }
            }
        }
    }

    @Override
    @SneakyThrows
    public void updateNameById(long id, String name) {
        Connection conn = config.getConnection();

        try(PreparedStatement preparedStatement = conn.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, id);

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void updateNameByDesignator(String designator, String name) {}

    @Override
    @SneakyThrows
    public void removeById(long id) {
        Connection conn = config.getConnection();

        try(PreparedStatement preparedStatement = conn.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void removeByDesignator(String designator) {}

    @Override
    @SneakyThrows
    public boolean isExistsById(long id) {
        Connection conn = config.getConnection();

        try (PreparedStatement preparedStatement = conn.prepareStatement(EXISTS_BY_ID)) {
            preparedStatement.setLong(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return resultSet.getBoolean("exists");
            }
        }
    }

    @Override
    @SneakyThrows
    public boolean isExistsByName(String name) {
        Connection conn = config.getConnection();

        try (PreparedStatement preparedStatement = conn.prepareStatement(EXISTS_BY_NAME)) {
            preparedStatement.setString(1, name);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return resultSet.getBoolean("exists");
            }
        }
    }

}
