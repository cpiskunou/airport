package by.piskunou.solvdlaba.persistent.impl.mapper;

import by.piskunou.solvdlaba.domain.Airport;
import lombok.SneakyThrows;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;

@Component
public class AirportMapper implements RowMapper<Airport> {

    @Override
    @SneakyThrows
    public Airport mapRow(ResultSet rs, int rowNum) {
        return new Airport(rs.getLong("airport_id"),
                           rs.getString("airport_name"));
    }

    @SneakyThrows
    public Airport mapFromRow(ResultSet rs) {
        return new Airport(rs.getLong("airport_from_id"),
                           rs.getString("airport_from_name"));
    }

    @SneakyThrows
    public Airport mapToRow(ResultSet rs) {
        return new Airport(rs.getLong("airport_to_id"),
                           rs.getString("airport_to_name"));
    }
}
