package by.piskunou.solvdlaba.repository.mapper;

import by.piskunou.solvdlaba.domain.Airport;
import by.piskunou.solvdlaba.domain.City;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CityMapper implements RowMapper<City> {

    private final AirportMapper airportMapper;

    @Override
    @SneakyThrows
    public City mapRow(ResultSet rs, int rowNum) {
        return new City(rs.getLong("city_id"),
                        rs.getString("city_name"));
    }

    @SneakyThrows
    public City airportsMapRow(ResultSet rs) {
        City city = mapRow(rs, 6);
        List<Airport> airports = new LinkedList<>();

        rs.previous();
        while(rs.next()) {
            if(city.getId() != rs.getLong("city_id")) {
                break;
            }

            Airport airport = airportMapper.mapRow(rs, 6);
            airports.add(airport);
        }

        city.setAirports(airports);

        return city;
    }
}
