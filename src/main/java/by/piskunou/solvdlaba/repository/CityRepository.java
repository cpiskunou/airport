package by.piskunou.solvdlaba.repository;

import by.piskunou.solvdlaba.domain.City;
import by.piskunou.solvdlaba.domain.Country;
import by.piskunou.solvdlaba.repository.config.DataSourceConfig;
import by.piskunou.solvdlaba.repository.mapper.CityMapper;
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
public class CityRepository {

    private static final Logger logger = LogManager.getLogger();

    private final DataSourceConfig config;

    private final CityMapper cityMapper;

    private static final String SCHEMA = "piskunou";

    public List<City> findAll() {
        try {
            Connection conn = config.getConnection();
            conn.setSchema(SCHEMA);
            PreparedStatement preparedStatement =
                    conn.prepareStatement("""
                            select id as city_id,
                            name as city_name
                            from city""");

            ResultSet resultSet = preparedStatement.executeQuery();

            List<City> cities = new LinkedList<>();

            while(resultSet.next()) {
                City city = cityMapper.mapRow(resultSet, 2);
                cities.add(city);
            }

            return cities;
        } catch (SQLException e) {
            logger.warn("SQLException: Didn't find all cities");
        }
        return Collections.emptyList();
    }

    public Optional<City> findById(long id) {
        try {
            Connection conn = config.getConnection();
            conn.setSchema(SCHEMA);
            PreparedStatement preparedStatement =
                    conn.prepareStatement("""
                            select id as city_id,
                            name as city_name
                            from city where id = ?""");

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            City city = cityMapper.mapRow(resultSet, 2);

            return Optional.of(city);
        } catch (SQLException e) {
            logger.warn("SQLException: Didn't find a city by id");
        }
        return Optional.empty();
    }

    public Optional<City> findCityAirports(long id) {
        try {
            Connection conn = config.getConnection();
            conn.setSchema(SCHEMA);
            PreparedStatement preparedStatement = conn.prepareStatement("""
                        select city.id as city_id,
                               city.name as city_name,
                               airport.id as airport_id,
                               airport.name as airport_name
                        from city join airport on city.id = airport.fk_city_id 
                        where city.id = ?""",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            City city = cityMapper.airportsMapRow(resultSet);

            return Optional.of(city);
        } catch (SQLException e) {
            logger.warn("SQLException: Didn't find a city with airports by id");
        }
        return Optional.empty();
    }

    public Optional<City> findByName(String name) {
        try {
            Connection conn = config.getConnection();
            conn.setSchema(SCHEMA);
            PreparedStatement preparedStatement =
                    conn.prepareStatement("""
                            select id as city_id,
                            name as city_name
                            from city where name = ?""");

            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            City city = cityMapper.mapRow(resultSet, 2);

            return Optional.of(city);
        } catch (SQLException e) {
            logger.warn("SQLException: Didn't find a city by name");
        }
        return Optional.empty();
    }

    public Optional<City> create(City city, long countryId) {
        try {
            Connection conn = config.getConnection();
            conn.setSchema(SCHEMA);
            PreparedStatement preparedStatement =
                    conn.prepareStatement("insert into city(fk_country_id, name) values(?, ?)",
                            Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setLong(1, countryId);
            preparedStatement.setString(2, city.getName());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();

            city.setId(resultSet.getLong("id"));

            return Optional.of(city);
        } catch (SQLException e) {
            logger.error("SQLException: Didn't create a city");
        }
        return Optional.empty();
    }

    public Optional<City> updateNameById(long id, String name) {
        try {
            Connection conn = config.getConnection();
            conn.setSchema(SCHEMA);
            PreparedStatement preparedStatement =
                    conn.prepareStatement("update city set name = ? where id = ?",
                            Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, id);

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();

            City city = new City(resultSet.getLong("id"),
                                 resultSet.getString("name"));

            return Optional.of(city);
        } catch (SQLException e) {
            logger.warn("SQLException: Didn't update a city");
        }
        return Optional.empty();
    }

    public void removeById(long id) {
        try {
            Connection conn = config.getConnection();
            conn.setSchema(SCHEMA);
            PreparedStatement preparedStatement =
                    conn.prepareStatement("delete from city where id = ?");

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQLException: Didn't remove a city by id");
        }
    }

    public void removeByName(String name) {
        try {
            Connection conn = config.getConnection();
            conn.setSchema(SCHEMA);
            PreparedStatement preparedStatement =
                    conn.prepareStatement("delete from city where name = ?");

            preparedStatement.setString(1, name);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQLException: Didn't remove a city by name");
        }
    }
}
