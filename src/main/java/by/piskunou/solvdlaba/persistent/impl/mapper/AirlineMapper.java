package by.piskunou.solvdlaba.persistent.impl.mapper;

import by.piskunou.solvdlaba.domain.Airline;
import lombok.SneakyThrows;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;

@Component
public class AirlineMapper implements RowMapper<Airline> {

    @Override
    @SneakyThrows
    public Airline mapRow(ResultSet rs, int rowNum) {
        return new Airline(rs.getLong("airline_id"),
                           rs.getString("airline_name"));
    }
}
