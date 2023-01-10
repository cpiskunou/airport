package by.piskunou.solvdlaba.repository;

import by.piskunou.solvdlaba.domain.Airline;
import by.piskunou.solvdlaba.repository.config.DataSourceConfig;
import by.piskunou.solvdlaba.repository.mapper.AirlineMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AirlineRepository {

    private static final Logger logger = LogManager.getLogger();

    private final DataSourceConfig config;

    private final AirlineMapper airlineMapper;

    private static final String SCHEMA = "piskunou";

    public List<Airline> findAll() {
        try {
            Connection conn = config.getConnection();
            conn.setSchema(SCHEMA);
            PreparedStatement preparedStatement =
                    conn.prepareStatement("""
                            select id as airline_id,
                            name as airline_name
                            from airline""");

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Airline> airlines = new LinkedList<>();

            while(resultSet.next()) {
                Airline airline = airlineMapper.mapRow(resultSet, 2);
                airlines.add(airline);
            }

            return airlines;
        } catch (SQLException e) {
            logger.warn("SQLException: Didn't find all airlines");
        }
        return Collections.emptyList();
    }

    public Optional<Airline> findById(long id) {
        try {
            Connection conn = config.getConnection();
            conn.setSchema(SCHEMA);
            PreparedStatement preparedStatement =
                    conn.prepareStatement("""
                            select id as airline_id,
                            name as airline_name
                            from airline where id = ?""");

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            Airline airline = airlineMapper.mapRow(resultSet, 2);

            return Optional.of(airline);
        } catch (SQLException e) {
            logger.warn("SQLException: Didn't find a airline by id");
        }
        return Optional.empty();
    }

    public Optional<Airline> findByName(String name) {
        try {
            Connection conn = config.getConnection();
            conn.setSchema(SCHEMA);
            PreparedStatement preparedStatement =
                    conn.prepareStatement("""
                            select id as airline_id,
                            name as airline_name
                            from airline where name = ?""");

            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            Airline airline = airlineMapper.mapRow(resultSet, 2);

            return Optional.of(airline);
        } catch (SQLException e) {
            logger.warn("SQLException: Didn't find a airline by name");
        }
        return Optional.empty();
    }

    public Optional<Airline> create(Airline airline) {
        try {
            Connection conn = config.getConnection();
            conn.setSchema(SCHEMA);
            PreparedStatement preparedStatement =
                    conn.prepareStatement("insert into airline(name) values(?)",
                            Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, airline.getName());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();

            airline.setId(resultSet.getLong("id"));

            return Optional.of(airline);
        } catch (SQLException e) {
            logger.warn("SQLException: An airline wasn't created");
        }
        return Optional.empty();
    }

    public Optional<Airline> updateNameById(long id, String name) {
        try {
            Connection conn = config.getConnection();
            conn.setSchema(SCHEMA);
            PreparedStatement preparedStatement =
                    conn.prepareStatement("update airline set name = ? where id = ?",
                            Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, id);

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();

            Airline airline = new Airline(resultSet.getLong("id"),
                                          resultSet.getString("name"));

            return Optional.of(airline);
        } catch (SQLException e) {
            logger.warn("SQLException: An airline wasn't updated");
        }
        return Optional.empty();
    }

    public void removeById(long id) {
        try {
            Connection conn = config.getConnection();
            conn.setSchema(SCHEMA);
            PreparedStatement preparedStatement =
                    conn.prepareStatement("delete from airline where id = ?");

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.warn("SQLException: An airline wasn't removed");
        }
    }
}
