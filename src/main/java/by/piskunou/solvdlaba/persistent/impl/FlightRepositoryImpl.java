package by.piskunou.solvdlaba.persistent.impl;

import by.piskunou.solvdlaba.domain.Flight;
import by.piskunou.solvdlaba.domain.Seat;
import by.piskunou.solvdlaba.persistent.FlightRepository;
import by.piskunou.solvdlaba.persistent.config.DataSourceConfig;
import by.piskunou.solvdlaba.persistent.impl.mapper.FlightMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

@Repository
@RequiredArgsConstructor
@Slf4j
public class FlightRepositoryImpl implements FlightRepository {

    private final DataSourceConfig config;
    private final FlightMapper flightMapper;
    private final Gson gson;

    @Override
    public Optional<Flight> findById(long id) {
        try {
            Connection conn = config.getConnection();

            try(PreparedStatement preparedStatement = conn.prepareStatement(FIND_BY_ID,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY)) {

                preparedStatement.setLong(1, id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    resultSet.next();

                    Flight flight = flightMapper.mapFindRow(resultSet);

                    return Optional.of(flight);
                }
            }
        } catch (SQLException e) {
            log.warn("SQLException: A flight with such id wasn't found");
        }
        return Optional.empty();
    }

    @Override
    public void create(Flight flight) {
        try {
            Connection conn = config.getConnection();

            try(PreparedStatement preparedStatement
                        = conn.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {

                preparedStatement.setLong(1, flight.getFrom().getId());
                preparedStatement.setLong(2, flight.getTo().getId());
                preparedStatement.setLong(3, flight.getAirline().getId());
                preparedStatement.setLong(4, flight.getAirplane().getId());
                preparedStatement.setTimestamp(5, Timestamp.valueOf(flight.getDepartureTime()));
                preparedStatement.setTimestamp(6, Timestamp.valueOf(flight.getArrivalTime()));
                preparedStatement.setBigDecimal(7, flight.getPrice());

                Type listType = new TypeToken<List<Seat>>(){}.getType();
                String json = gson.toJson(flight.getSeats(), listType);

                preparedStatement.setObject(8, json);
                preparedStatement.executeUpdate();

                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    resultSet.next();

                    Long id = resultSet.getLong("id");

                    flight.setId(id);
                }
            }
        } catch (SQLException e) {
            log.warn("SQLException: Didn't create a flight");
        }
    }

    @Override
    public List<Flight> search(Long[] fromAirports, Long[] toAirports, int passengerAmount,
                               LocalDateTime start, LocalDateTime end) {
        try {
            Connection conn = config.getConnection();
            try(PreparedStatement preparedStatement = conn.prepareStatement(SEARCH,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY)) {

                preparedStatement.setArray(1, conn.createArrayOf("bigint", fromAirports));
                preparedStatement.setArray(2, conn.createArrayOf("bigint", toAirports));
                preparedStatement.setTimestamp(3, Timestamp.valueOf(start));
                preparedStatement.setTimestamp(4, Timestamp.valueOf(end));

                try(ResultSet resultSet = preparedStatement.executeQuery()) {
                    List<Flight> flights = new LinkedList<>();
                    while(resultSet.next()) {
                        Flight flight = flightMapper.mapSearchRow(resultSet);
                        flights.add(flight);
                    }

                   return flights;
                }
            }
        } catch (SQLException e) {
            log.warn("SQLException: Didn't find flights with such params");
        }
        return Collections.emptyList();
    }

    @Override
    public List<Seat> freeSeats(long id) {
        try {
            Connection conn = config.getConnection();

            try (PreparedStatement preparedStatement = conn.prepareStatement(FREE_SEATS)) {
                preparedStatement.setLong(1, id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    resultSet.next();

                    Type listType = new TypeToken<List<Seat>>() {}.getType();

                    String json = resultSet.getString("free_seats");

                    return gson.fromJson(json, listType);
                }
            }
        } catch (SQLException e) {
            log.warn("SQLException: Didn't find free seats");
        }
        return Collections.emptyList();
    }
}
