package by.piskunou.solvdlaba.repository.mapper;

import by.piskunou.solvdlaba.domain.User;
import lombok.SneakyThrows;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserMapper implements RowMapper<User> {

    @Override
    @SneakyThrows
    public User mapRow(ResultSet rs, int rowNum) {
        return new User(rs.getLong("id"),
                        rs.getString("username"));
    }

    @SneakyThrows
    public User ticketsMapRow(ResultSet resultSet) {
        return null;
    }
}
