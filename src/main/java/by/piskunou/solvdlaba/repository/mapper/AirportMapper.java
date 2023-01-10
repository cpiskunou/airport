package by.piskunou.solvdlaba.repository.mapper;

import by.piskunou.solvdlaba.domain.Airport;
import by.piskunou.solvdlaba.domain.City;
import lombok.SneakyThrows;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AirportMapper implements RowMapper<Airport> {

    @Override
    @SneakyThrows
    public Airport mapRow(ResultSet rs, int rowNum) {
        return new Airport(rs.getLong("airport_id"),
                           rs.getString("airport_name"));
    }
}
