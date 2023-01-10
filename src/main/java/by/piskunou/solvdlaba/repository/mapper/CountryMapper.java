package by.piskunou.solvdlaba.repository.mapper;

import by.piskunou.solvdlaba.domain.City;
import by.piskunou.solvdlaba.domain.Country;
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
public class CountryMapper implements RowMapper<Country> {

    private final CityMapper cityMapper;

    @Override
    @SneakyThrows
    public Country mapRow(ResultSet rs, int rowNum) {
        return new Country(rs.getLong("country_id"),
                           rs.getString("country_name"));
    }

    @SneakyThrows
    public Country citiesMapRow(ResultSet rs) throws SQLException {
        Country country = mapRow(rs, 4);
        List<City> cities = new LinkedList<>();

        rs.previous();
        while(rs.next()) {
            City city = cityMapper.mapRow(rs, 4);
            cities.add(city);
        }

        country.setCities(cities);

        return country;
    }

    @SneakyThrows
    public Country airportsMapRow(ResultSet rs) {
        Country country = mapRow(rs, 6);
        List<City> cities = new LinkedList<>();

        rs.previous();
        while(rs.next()) {
            City city = cityMapper.airportsMapRow(rs);
            cities.add(city);

            rs.previous();
        }

        country.setCities(cities);

        return country;
    }
}
