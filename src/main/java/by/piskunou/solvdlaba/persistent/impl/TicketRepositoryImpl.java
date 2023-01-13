package by.piskunou.solvdlaba.persistent.impl;

import by.piskunou.solvdlaba.domain.Ticket;
import by.piskunou.solvdlaba.persistent.TicketRepository;
import by.piskunou.solvdlaba.persistent.config.DataSourceConfig;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@Repository
@RequiredArgsConstructor
public class TicketRepositoryImpl implements TicketRepository {

    private final DataSourceConfig config;

    private static final String CREATE = """
            insert into ticket(fk_owner_id, fk_flight_id, fk_passenger_id, seat_no)
            values (?, ?, ?, ?)""";

    @Override
    @SneakyThrows
    public void create(Ticket ticket, long userId) {
        Connection conn = config.getConnection();

        try(PreparedStatement preparedStatement =
                    conn.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, ticket.getFlight().getId());
            preparedStatement.setLong(3, ticket.getPassenger().getId());
            preparedStatement.setString(4, ticket.getSeat().getNumber());
            preparedStatement.executeUpdate();

            try(ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if(resultSet.next()) {
                    Long id = resultSet.getLong("id");

                    ticket.setId(id);
                }
            }
        }
    }

}
