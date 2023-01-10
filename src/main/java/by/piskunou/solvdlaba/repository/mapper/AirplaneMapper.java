package by.piskunou.solvdlaba.repository.mapper;

import by.piskunou.solvdlaba.domain.Airplane;
import lombok.SneakyThrows;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AirplaneMapper implements RowMapper<Airplane> {

    @Override
    @SneakyThrows
    public Airplane mapRow(ResultSet rs, int rowNum) {
        return new Airplane(rs.getLong("id"),
                            rs.getString("model"),
                            rs.getByte("row_seat_no"),
                            rs.getShort("row_no"));
    }
}
