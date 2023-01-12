package by.piskunou.solvdlaba.persistent.impl;

import by.piskunou.solvdlaba.domain.Airline;
import by.piskunou.solvdlaba.persistent.AirlineRepository;
import by.piskunou.solvdlaba.persistent.config.DataSourceConfig;
import by.piskunou.solvdlaba.persistent.impl.mapper.AirlineMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class AirlineRepositoryImpl implements AirlineRepository {

    private final DataSourceConfig config;
    private final AirlineMapper airlineMapper;

    @Override
    public List<Airline> findAll() {
        try {
            Connection conn = config.getConnection();

            try (PreparedStatement preparedStatement =
                    conn.prepareStatement("""
                            select id as airline_id,
                            name as airline_name
                            from airline""")) {

                try(ResultSet resultSet = preparedStatement.executeQuery()) {

                    List<Airline> airlines = new LinkedList<>();

                    while (resultSet.next()) {
                        Airline airline = airlineMapper.mapRow(resultSet, 2);
                        airlines.add(airline);
                    }

                    return airlines;
                }
            }
        } catch (SQLException e) {
            log.warn("SQLException: Didn't find all airlines");
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<Airline> findById(long id) {
        try {
            Connection conn = config.getConnection();

            try(PreparedStatement preparedStatement =
                    conn.prepareStatement("""
                            select id as airline_id,
                            name as airline_name
                            from airline where id = ?""")) {

                preparedStatement.setLong(1, id);

                try(ResultSet resultSet = preparedStatement.executeQuery()) {
                    resultSet.next();

                    Airline airline = airlineMapper.mapRow(resultSet, 2);

                    return Optional.of(airline);
                }
            }
        } catch (SQLException e) {
            log.warn("SQLException: Didn't find a airline by id");
        }
        return Optional.empty();
    }

    @Override
    public Optional<Airline> findByName(String name) {
        try {
            Connection conn = config.getConnection();

            try(PreparedStatement preparedStatement =
                    conn.prepareStatement("""
                            select id as airline_id,
                            name as airline_name
                            from airline where name = ?""")) {

                preparedStatement.setString(1, name);

                try(ResultSet resultSet = preparedStatement.executeQuery()) {
                    resultSet.next();

                    Airline airline = airlineMapper.mapRow(resultSet, 2);

                    return Optional.of(airline);
                }
            }
        } catch (SQLException e) {
            log.warn("SQLException: Didn't find a airline by name");
        }
        return Optional.empty();
    }

    @Override
    public Optional<Long> create(Airline airline) {
        try {
            Connection conn = config.getConnection();

            try(PreparedStatement preparedStatement =
                    conn.prepareStatement("insert into airline(name) values(?)",
                            Statement.RETURN_GENERATED_KEYS)) {

                preparedStatement.setString(1, airline.getName());
                preparedStatement.executeUpdate();

                try(ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    resultSet.next();

                    Long id = resultSet.getLong("id");

                    return Optional.of(id);
                }
            }
        } catch (SQLException e) {
            log.warn("SQLException: An airline wasn't created");
        }
        return Optional.empty();
    }

    @Override
    public Optional<Airline> updateNameById(long id, String name) {
        try {
            Connection conn = config.getConnection();

            try(PreparedStatement preparedStatement =
                    conn.prepareStatement("update airline set name = ? where id = ?",
                            Statement.RETURN_GENERATED_KEYS)) {

                preparedStatement.setString(1, name);
                preparedStatement.setLong(2, id);

                preparedStatement.executeUpdate();

                try(ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    resultSet.next();

                    Airline airline = new Airline(resultSet.getLong("id"),
                            resultSet.getString("name"));

                    return Optional.of(airline);
                }
            }
        } catch (SQLException e) {
            log.warn("SQLException: An airline wasn't updated");
        }
        return Optional.empty();
    }

    @Override
    public void removeById(long id) {
        try {
            Connection conn = config.getConnection();

            try(PreparedStatement preparedStatement =
                    conn.prepareStatement("delete from airline where id = ?")) {

                preparedStatement.setLong(1, id);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            log.warn("SQLException: An airline wasn't removed");
        }
    }
}
