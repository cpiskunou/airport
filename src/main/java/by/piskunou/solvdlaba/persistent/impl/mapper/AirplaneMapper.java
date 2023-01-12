package by.piskunou.solvdlaba.persistent.impl.mapper;

import by.piskunou.solvdlaba.domain.Airplane;
import lombok.SneakyThrows;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;

@Component
public class AirplaneMapper implements RowMapper<Airplane> {

    @Override
    @SneakyThrows
    public Airplane mapRow(ResultSet rs, int rowNum) {
        return new Airplane(rs.getLong("airplane_id"),
                            rs.getString("airplane_model"),
                            rs.getByte("airplane_seats_in_row"),
                            rs.getShort("airplane_row_no"));
    }
}
