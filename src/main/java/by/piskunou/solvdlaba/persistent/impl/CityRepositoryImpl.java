package by.piskunou.solvdlaba.persistent.impl;

import by.piskunou.solvdlaba.domain.City;
import by.piskunou.solvdlaba.persistent.CityRepository;
import by.piskunou.solvdlaba.persistent.config.DataSourceConfig;
import by.piskunou.solvdlaba.persistent.impl.mapper.CityMapper;
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
public class CityRepositoryImpl implements CityRepository {

    private final DataSourceConfig config;
    private final CityMapper cityMapper;

    @Override
    public List<City> findAll() {
        try {
            Connection conn = config.getConnection();

            try(PreparedStatement preparedStatement =
                    conn.prepareStatement("""
                            select id as city_id,
                            name as city_name
                            from city""")) {

                try(ResultSet resultSet = preparedStatement.executeQuery()) {
                    List<City> cities = new LinkedList<>();

                    while (resultSet.next()) {
                        City city = cityMapper.mapRow(resultSet, 2);
                        cities.add(city);
                    }

                    return cities;
                }
            }
        } catch (SQLException e) {
            log.warn("SQLException: Didn't find all cities");
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<City> findById(long id) {
        try {
            Connection conn = config.getConnection();

            try(PreparedStatement preparedStatement =
                    conn.prepareStatement("""
                            select id as city_id,
                            name as city_name
                            from city where id = ?""")) {

                preparedStatement.setLong(1, id);

                try(ResultSet resultSet = preparedStatement.executeQuery()) {
                    resultSet.next();

                    City city = cityMapper.mapRow(resultSet, 2);

                    return Optional.of(city);
                }
            }
        } catch (SQLException e) {
            log.warn("SQLException: Didn't find a city by id");
        }
        return Optional.empty();
    }

    @Override
    public Optional<City> findCityAirports(long id) {
        try {
            Connection conn = config.getConnection();

            try(PreparedStatement preparedStatement = conn.prepareStatement("""
                        select city.id as city_id,
                               city.name as city_name,
                               airport.id as airport_id,
                               airport.name as airport_name
                        from city join airport on city.id = airport.fk_city_id 
                        where city.id = ?""",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY)) {

                preparedStatement.setLong(1, id);

                try(ResultSet resultSet = preparedStatement.executeQuery()) {
                    resultSet.next();

                    City city = cityMapper.airportsMapRow(resultSet);

                    return Optional.of(city);
                }
            }
        } catch (SQLException e) {
            log.warn("SQLException: Didn't find a city with airports by id");
        }
        return Optional.empty();
    }

    @Override
    public Optional<City> findByName(String name) {
        try {
            Connection conn = config.getConnection();

            try(PreparedStatement preparedStatement =
                    conn.prepareStatement("""
                            select id as city_id,
                            name as city_name
                            from city where name = ?""")) {

                preparedStatement.setString(1, name);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    resultSet.next();

                    City city = cityMapper.mapRow(resultSet, 2);

                    return Optional.of(city);
                }
            }
        } catch (SQLException e) {
            log.warn("SQLException: Didn't find a city by name");
        }
        return Optional.empty();
    }

    @Override
    public void create(City city, long countryId) {
        try {
            Connection conn = config.getConnection();

            try(PreparedStatement preparedStatement =
                    conn.prepareStatement("insert into city(fk_country_id, name) values(?, ?)",
                            Statement.RETURN_GENERATED_KEYS)) {

                preparedStatement.setLong(1, countryId);
                preparedStatement.setString(2, city.getName());
                preparedStatement.executeUpdate();

                try(ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    resultSet.next();

                    Long id = resultSet.getLong("id");

                    city.setId(id);
                }
            }
        } catch (SQLException e) {
            log.error("SQLException: Didn't create a city");
        }
    }

    @Override
    public void updateNameById(long id, String name) {
        try {
            Connection conn = config.getConnection();

            try(PreparedStatement preparedStatement =
                    conn.prepareStatement("update city set name = ? where id = ?",
                            Statement.RETURN_GENERATED_KEYS)) {

                preparedStatement.setString(1, name);
                preparedStatement.setLong(2, id);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            log.warn("SQLException: Didn't update a city");
        }
    }

    @Override
    public void removeById(long id) {
        try {
            Connection conn = config.getConnection();

            try(PreparedStatement preparedStatement =
                    conn.prepareStatement("delete from city where id = ?")) {

                preparedStatement.setLong(1, id);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            log.error("SQLException: Didn't remove a city by id");
        }
    }
}
