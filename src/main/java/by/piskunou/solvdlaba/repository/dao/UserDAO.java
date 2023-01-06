package by.piskunou.solvdlaba.repository.dao;

import by.piskunou.solvdlaba.domain.User;
import by.piskunou.solvdlaba.domain.enums.Authority;
import by.piskunou.solvdlaba.repository.sql.UserSql;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDAO {
    private static final Logger logger = LogManager.getLogger();
    private final DataSource dataSource;
    public void save(User user) {}

    public Optional<User> findById(long id) {
        try(Connection conn = dataSource.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(UserSql.FIND_BY_ID);

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            User user = new User();

            user.setId(resultSet.getLong("id"));
            user.setUsername(resultSet.getString("username"));
            user.setAuthority(Authority.valueOf(resultSet.getString("authority")));

            return Optional.of(user);
        } catch (SQLException e) {
            logger.error("SQLException: Find by id");
        }
        return Optional.empty();
    }

    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }

    public Optional<User> findByUsernameAndByIdNot(long id, String username) {
        return Optional.empty();
    }

    public List<User> findAll() {
        return Collections.emptyList();
    }

    public void removeById(long id) {}
}
