package by.piskunou.solvdlaba.persistent.impl;

import by.piskunou.solvdlaba.domain.User;
import by.piskunou.solvdlaba.persistent.UserRepository;
import by.piskunou.solvdlaba.persistent.config.DataSourceConfig;
import by.piskunou.solvdlaba.persistent.impl.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final DataSourceConfig config;
    private final UserMapper userMapper;

    @Override
    @SneakyThrows
    public void register(User user) {
        Connection conn = config.getConnection();

        try(PreparedStatement preparedStatement =
                    conn.prepareStatement(REGISTER, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.executeUpdate();

            try(ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if(resultSet.next()) {
                    Long id = resultSet.getLong("id");

                    user.setId(id);
                }
            }
        }
    }

    @Override
    @SneakyThrows
    public Optional<User> findById(long id) {
        Connection conn = config.getConnection();

        try(PreparedStatement preparedStatement = conn.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                    User user = userMapper.mapRow(resultSet, 2);

                    return Optional.of(user);
                }
                return Optional.empty();
            }
        }
    }

    @Override
    @SneakyThrows
    public Optional<User> findByUsername(String username) {
        Connection conn = config.getConnection();

        try(PreparedStatement preparedStatement = conn.prepareStatement(FIND_BY_USERNAME)) {
            preparedStatement.setString(1, username);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                    User user = userMapper.mapRow(resultSet, 2);

                    return Optional.of(user);
                }
                return Optional.empty();
            }
        }
    }

    @Override
    @SneakyThrows
    public List<User> findAll() {
        Connection conn = config.getConnection();

        try(PreparedStatement preparedStatement = conn.prepareStatement(FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery()) {

            List<User> users = new LinkedList<>();

            while (resultSet.next()) {
                User user = userMapper.mapRow(resultSet, 2);
                users.add(user);
            }
            return users;
        }
    }

    @Override
    @SneakyThrows
    public Optional<User> findUserTickets(long id) {
        Connection conn = config.getConnection();

        try(PreparedStatement preparedStatement = conn.prepareStatement(FIND_TICKETS_BY_ID,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY)) {

            preparedStatement.setLong(1, id);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                    User user = userMapper.ticketsMapRow(resultSet);

                    return Optional.of(user);
                }
                return Optional.empty();
            }
        }
    }

    @Override
    @SneakyThrows
    public void updateUsernameById(long id, String username) {
        Connection conn = config.getConnection();

        try(PreparedStatement preparedStatement = conn.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, username);
            preparedStatement.setLong(2, id);

            preparedStatement.executeUpdate();
        }
    }

    @Override
    @SneakyThrows
    public void removeById(long id) {
        Connection conn = config.getConnection();

        try(PreparedStatement preparedStatement = conn.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        }
    }

}
