package by.piskunou.solvdlaba.persistent.impl;

import by.piskunou.solvdlaba.domain.User;
import by.piskunou.solvdlaba.persistent.UserRepository;
import by.piskunou.solvdlaba.persistent.config.DataSourceConfig;
import by.piskunou.solvdlaba.persistent.impl.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.*;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryImpl implements UserRepository {

    private final DataSourceConfig config;
    private final UserMapper userMapper;

    @Override
    public void register(User user) {
        try {
            Connection conn = config.getConnection();

            try(PreparedStatement preparedStatement =
                    conn.prepareStatement("insert into \"user\" (username) values(?)",
                                                Statement.RETURN_GENERATED_KEYS)) {

                preparedStatement.setString(1, user.getUsername());
                preparedStatement.executeUpdate();

                try(ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    resultSet.next();

                    Long id = resultSet.getLong("id");

                    user.setId(id);
                }
            }
        } catch (SQLException e) {
            log.error("SQLException: Didn't register");
        }
    }

    @Override
    public Optional<User> findById(long id) {
        try {
            Connection conn = config.getConnection();

            try(PreparedStatement preparedStatement =
                    conn.prepareStatement("select id, username from \"user\" where id = ?")) {

                preparedStatement.setLong(1, id);

                try(ResultSet resultSet = preparedStatement.executeQuery()) {
                    resultSet.next();

                    User user = userMapper.mapRow(resultSet, 2);

                    return Optional.of(user);
                }
            }
        } catch (SQLException e) {
            log.warn("SQLException: Didn't find by id");
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        try {
            Connection conn = config.getConnection();

            try(PreparedStatement preparedStatement =
                    conn.prepareStatement("select id, username from \"user\" where username = ?")) {

                preparedStatement.setString(1, username);

                try(ResultSet resultSet = preparedStatement.executeQuery()) {
                    resultSet.next();

                    User user = userMapper.mapRow(resultSet, 2);

                    return Optional.of(user);
                }
            }
        } catch (SQLException e) {
            log.warn("SQLException: Didn't find by username");
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        try {
            Connection conn = config.getConnection();

            try(PreparedStatement preparedStatement =
                    conn.prepareStatement("select id, username from \"user\"")) {

                try(ResultSet resultSet = preparedStatement.executeQuery()) {
                    List<User> users = new LinkedList<>();

                    while (resultSet.next()) {
                        User user = userMapper.mapRow(resultSet, 2);
                        users.add(user);
                    }

                    return users;
                }
            }
        } catch (SQLException e) {
            log.error("SQLException: Didn't find all");
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<User> findUserTickets(long id) {
        try {
            Connection conn = config.getConnection();

            //TODO: write whole join
            try(PreparedStatement preparedStatement = conn.prepareStatement("""
                        select user.id,
                               user.username,
                               ticket.id as ticket_id,
                               ticket.price as ticket_price,
                               ticket.seat_no as ticket_seat_no,
                               ticket.type as ticket_type
                        from user join ticket on user.id = ticket.fk_owner_id
                        where user.id = ?""",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY)) {

                preparedStatement.setLong(1, id);

                try(ResultSet resultSet = preparedStatement.executeQuery()) {
                    resultSet.next();

                    User user = userMapper.ticketsMapRow(resultSet);

                    return Optional.of(user);
                }
            }
        } catch (SQLException e) {
            log.warn("SQLException: Didn't find an user with tickets by id");
        }
        return Optional.empty();
    }

    @Override
    public void removeById(long id) {
        try {
            Connection conn = config.getConnection();

            try(PreparedStatement preparedStatement =
                    conn.prepareStatement("delete from \"user\" where id = ?")) {

                preparedStatement.setLong(1, id);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            log.error("SQLException: Didn't remove by id");
        }
    }

    @Override
    public void updateUsernameById(long id, String username) {
        try {
            Connection conn = config.getConnection();

            try(PreparedStatement preparedStatement =
                    conn.prepareStatement("update \"user\" set username = ? where id = ?")) {

                preparedStatement.setString(1, username);
                preparedStatement.setLong(2, id);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            log.warn("SQLException: Didn't update a user");
        }
    }
}
