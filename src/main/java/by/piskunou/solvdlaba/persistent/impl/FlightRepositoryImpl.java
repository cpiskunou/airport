package by.piskunou.solvdlaba.persistent.impl;

import by.piskunou.solvdlaba.domain.Flight;
import by.piskunou.solvdlaba.domain.Seat;
import by.piskunou.solvdlaba.persistent.FlightRepository;
import by.piskunou.solvdlaba.persistent.config.DataSourceConfig;
import by.piskunou.solvdlaba.persistent.impl.mapper.FlightMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class FlightRepositoryImpl implements FlightRepository {

    private final DataSourceConfig config;
    private final FlightMapper flightMapper;
    private final Gson gson;

    private static final String FIND_BY_ID = """
            select flight.id as flight_id,
                       
                   airport_from.id as airport_from_id,
                   airport_from.name as airport_from_name,
                       
                   airport_to.id as airport_to_id,
                   airport_to.name as airport_to_name,
                       
                   airplane.id as airplane_id,
                   airplane.model as airplane_model,
                   airplane.seats_in_row as airplane_seats_in_row,
                   airplane.row_no as airplane_row_no,
                       
                   airline.id as airline_id,
                   airline.name as airline_name,
                       
                   flight.departure_time as flight_departure_time,
                   flight.arrival_time as flight_arrival_time,
                   flight.price as flight_price
            from flight inner join airport airport_from on flight.fk_airport_from_id = airport_from.id
                        inner join airport airport_to on flight.fk_airport_to_id = airport_to.id
                        inner join airline on flight.fk_airline_id = airline.id
                        inner join airplane on flight.fk_airplane_id = airplane.id
            where flight.id = ?;
            """;

    private static final String CREATE = """
            insert into flight(fk_airport_from_id,
                               fk_airport_to_id,
                               fk_airline_id,
                               fk_airplane_id,
                               departure_time,
                               arrival_time,
                               price,
                               free_seats)
            values (?, ?, ?, ?, ?, ?, ?, ?::jsonb)
            """;

    private static final String SEARCH = """
            select flight.id as flight_id,
                    flight.departure_time as flight_departure_time,
                    flight.arrival_time as flight_arrival_time,
                    flight.price as flight_price,
                    
                    airport_from.id as airport_from_id,
                    airport_from.name as airport_from_name,
                    
                    airport_to.id as airport_to_id,
                    airport_to.name as airport_to_name,
                    
                    airline.id as airline_id,
                    airline.name as airline_name
                       
            from flight inner join airport airport_from on flight.fk_airport_from_id = airport_from.id
                        inner join airport airport_to on flight.fk_airport_to_id = airport_to.id
                        inner join airline on flight.fk_airline_id = airline.id
            where airport_from.id = any(?) and airport_to.id = any(?) and
                  flight.departure_time between ? and ?;              
            """;

    private static final String FREE_SEATS = """
            select jsonb_path_query_array(flight.free_seats, '$[*] ? (@.free == false)') as free_seats
            from flight where flight.id = ?;
            """;

    @Override
    @SneakyThrows
    public Optional<Flight> findById(long id) {
        Connection conn = config.getConnection();

        try(PreparedStatement preparedStatement = conn.prepareStatement(FIND_BY_ID,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY)) {

            preparedStatement.setLong(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Flight flight = flightMapper.mapFindRow(resultSet);

                    return Optional.of(flight);
                }
                return Optional.empty();
            }
        }
    }

    @Override
    @SneakyThrows
    public void create(Flight flight) {
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
                if(resultSet.next()) {
                    Long id = resultSet.getLong("id");

                    flight.setId(id);
                }
            }
        }
    }

    @Override
    @SneakyThrows
    public List<Flight> search(Long[] fromAirports, Long[] toAirports, int passengerAmount,
                               LocalDateTime start, LocalDateTime end) {
        Connection conn = config.getConnection();
        try(PreparedStatement preparedStatement = conn.prepareStatement(SEARCH,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY)) {

            preparedStatement.setArray(1, conn.createArrayOf("bigint", fromAirports));
            preparedStatement.setArray(2, conn.createArrayOf("bigint", toAirports));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(start));
            preparedStatement.setTimestamp(4, Timestamp.valueOf(end));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Flight> flights = new LinkedList<>();
                while (resultSet.next()) {
                    Flight flight = flightMapper.mapSearchRow(resultSet);
                    flights.add(flight);
                }

                return flights;
            }
        }
    }

    @Override
    @SneakyThrows
    public List<Seat> freeSeats(long id) {
        Connection conn = config.getConnection();

        try (PreparedStatement preparedStatement = conn.prepareStatement(FREE_SEATS)) {
            preparedStatement.setLong(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                    Type listType = new TypeToken<List<Seat>>() {}.getType();

                    String json = resultSet.getString("free_seats");

                    return gson.fromJson(json, listType);
                }
                return Collections.emptyList();
            }
        }
    }
}
