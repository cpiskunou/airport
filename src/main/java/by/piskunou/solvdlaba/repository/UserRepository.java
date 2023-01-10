package by.piskunou.solvdlaba.repository;

import by.piskunou.solvdlaba.domain.User;
import by.piskunou.solvdlaba.repository.config.DataSourceConfig;
import by.piskunou.solvdlaba.repository.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private static final Logger logger = LogManager.getLogger();

    private final DataSourceConfig config;

    private final UserMapper userMapper;

    private static final String SCHEMA = "piskunou";

    public Optional<User> register(User user) {
        try {
            Connection conn = config.getConnection();
            conn.setSchema(SCHEMA);
            PreparedStatement preparedStatement =
                    conn.prepareStatement("insert into \"user\" (username) values(?)",
                                                Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();

            user.setId(resultSet.getLong("id"));

            return Optional.of(user);
        } catch (SQLException e) {
            logger.error("SQLException: Didn't register");
        }
        return Optional.empty();
    }

    public Optional<User> findById(long id) {
        try {
            Connection conn = config.getConnection();
            conn.setSchema(SCHEMA);
            PreparedStatement preparedStatement =
                    conn.prepareStatement("select id, username from \"user\" where id = ?");

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            User user = userMapper.mapRow(resultSet, 2);

            return Optional.of(user);
        } catch (SQLException e) {
            logger.warn("SQLException: Didn't find by id");
        }
        return Optional.empty();
    }

    public Optional<User> findByUsername(String username) {
        try {
            Connection conn = config.getConnection();
            conn.setSchema(SCHEMA);
            PreparedStatement preparedStatement =
                    conn.prepareStatement("select id, username from \"user\" where username = ?");

            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            User user = userMapper.mapRow(resultSet, 2);

            return Optional.of(user);
        } catch (SQLException e) {
            logger.warn("SQLException: Didn't find by username");
        }
        return Optional.empty();
    }

    public Optional<User> findByUsernameAndByIdNot(long id, String username) {
        try {
            Connection conn = config.getConnection();
            conn.setSchema(SCHEMA);
            PreparedStatement preparedStatement =
                    conn.prepareStatement("select id, username from \"user\" where username = ? and id != ?");

            preparedStatement.setString(1, username);
            preparedStatement.setLong(2, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            User user = userMapper.mapRow(resultSet, 2);

            return Optional.of(user);
        } catch (SQLException e) {
            logger.warn("SQLException: Didn't find by username and not by id");
        }
        return Optional.empty();
    }

    public List<User> findAll() {
        try {
            Connection conn = config.getConnection();
            conn.setSchema(SCHEMA);
            PreparedStatement preparedStatement =
                    conn.prepareStatement("select id, username from \"user\"");


            ResultSet resultSet = preparedStatement.executeQuery();

            List<User> users = new LinkedList<>();

            while(resultSet.next()) {
                User user = userMapper.mapRow(resultSet, 2);
                users.add(user);
            }

            return users;
        } catch (SQLException e) {
            logger.error("SQLException: Didn't find all");
        }
        return Collections.emptyList();
    }

    public Optional<User> findUserTickets(long id) {
        try {
            Connection conn = config.getConnection();
            conn.setSchema(SCHEMA);
            //TODO: write whole join
            PreparedStatement preparedStatement = conn.prepareStatement("""
                        select user.id,
                               user.username,
                               ticket.id as ticket_id,
                               ticket.price as ticket_price,
                               ticket.seat_no as ticket_seat_no,
                               ticket.type as ticket_type
                        from user join ticket on user.id = ticket.fk_owner_id
                        where user.id = ?""",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            User user = userMapper.ticketsMapRow(resultSet);

            return Optional.of(user);
        } catch (SQLException e) {
            logger.warn("SQLException: Didn't find an user with tickets by id");
        }
        return Optional.empty();
    }

    public void removeById(long id) {
        try {
            Connection conn = config.getConnection();
            conn.setSchema(SCHEMA);
            PreparedStatement preparedStatement =
                    conn.prepareStatement("delete from \"user\" where id = ?");

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQLException: Didn't remove by id");
        }
    }
    public Optional<User> updateUsernameById(long id, String username) {
        try {
            Connection conn = config.getConnection();
            conn.setSchema(SCHEMA);
            PreparedStatement preparedStatement =
                    conn.prepareStatement("update \"user\" set username = ? where id = ?",
                                            Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, username);
            preparedStatement.setLong(2, id);

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();

            User user = userMapper.mapRow(resultSet, 2);

            return Optional.of(user);
        } catch (SQLException e) {
            logger.warn("SQLException: Didn't update a user");
        }
        return Optional.empty();
    }
}
