package by.piskunou.solvdlaba.persistent.impl.mapper;

import by.piskunou.solvdlaba.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;

@Component
@RequiredArgsConstructor
public class FlightMapper implements RowMapper<Flight> {

    private final AirportMapper airportMapper;
    private final AirlineMapper airlineMapper;
    private final AirplaneMapper airplaneMapper;

    @Override
    @SneakyThrows
    public Flight mapRow(ResultSet rs, int rowNum) {
        return new Flight(rs.getLong("flight_id"),
                rs.getBigDecimal("flight_price"),
                rs.getTimestamp("flight_departure_time").toLocalDateTime(),
                rs.getTimestamp("flight_arrival_time").toLocalDateTime());
    }

    public Flight mapSearchRow(ResultSet rs) {
        Flight flight = mapRow(rs, 1);

        Airport airportFrom = airportMapper.mapFromRow(rs);
        Airport airportTo = airportMapper.mapToRow(rs);

        Airline airline = airlineMapper.mapRow(rs,1);

        flight.setTo(airportTo);
        flight.setFrom(airportFrom);
        flight.setAirline(airline);

        return flight;
    }

    public Flight mapFindRow(ResultSet rs) {
        Flight flight = mapSearchRow(rs);

        Airplane airplane = airplaneMapper.mapRow(rs,1);

        flight.setAirplane(airplane);

        return flight;
    }
}
