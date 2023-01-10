package by.piskunou.solvdlaba.repository.mapper;

import by.piskunou.solvdlaba.domain.Flight;
import lombok.SneakyThrows;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FlightMapper implements RowMapper<Flight> {
    @Override
    @SneakyThrows
    public Flight mapRow(ResultSet rs, int rowNum) {
        return null;
    }
}
