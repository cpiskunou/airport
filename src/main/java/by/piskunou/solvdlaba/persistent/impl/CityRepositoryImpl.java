package by.piskunou.solvdlaba.persistent.impl;

import by.piskunou.solvdlaba.domain.City;
import by.piskunou.solvdlaba.persistent.CityRepository;
import by.piskunou.solvdlaba.persistent.config.DataSourceConfig;
import by.piskunou.solvdlaba.persistent.impl.mapper.CityMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CityRepositoryImpl implements CityRepository {

    private final DataSourceConfig config;
    private final CityMapper cityMapper;

    @Override
    @SneakyThrows
    public List<City> findAll() {
        Connection conn = config.getConnection();

        try (PreparedStatement preparedStatement = conn.prepareStatement(FIND_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            List<City> cities = new LinkedList<>();

            while (resultSet.next()) {
                City city = cityMapper.mapRow(resultSet, 2);
                cities.add(city);
            }
            return cities;
        }
    }

    @Override
    @SneakyThrows
    public Optional<City> findById(long id) {
        Connection conn = config.getConnection();

        try (PreparedStatement preparedStatement = conn.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    City city = cityMapper.mapRow(resultSet, 2);

                    return Optional.of(city);
                }
                return Optional.empty();
            }
        }
    }

    @Override
    @SneakyThrows
    public Optional<City> findCityAirports(long id) {
        Connection conn = config.getConnection();

        try (PreparedStatement preparedStatement = conn.prepareStatement(FIND_AIRPORTS_BY_ID,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY)) {

            preparedStatement.setLong(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    City city = cityMapper.airportsMapRow(resultSet);

                    return Optional.of(city);
                }
                return Optional.empty();
            }
        }
    }

    @Override
    @SneakyThrows
    public Optional<City> findByName(String name) {
        Connection conn = config.getConnection();

        try (PreparedStatement preparedStatement = conn.prepareStatement(FIND_BY_NAME)) {
            preparedStatement.setString(1, name);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                    City city = cityMapper.mapRow(resultSet, 2);

                    return Optional.of(city);
                }
                return Optional.empty();
            }
        }
    }

    @Override
    @SneakyThrows
    public void create(City city, long countryId) {
        Connection conn = config.getConnection();

        try (PreparedStatement preparedStatement =
                     conn.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, countryId);
            preparedStatement.setString(2, city.getName());
            preparedStatement.executeUpdate();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if(resultSet.next()) {
                    Long id = resultSet.getLong("id");

                    city.setId(id);
                }
            }
        }
    }

    @Override
    @SneakyThrows
    public void updateNameById(long id, String name) {
        Connection conn = config.getConnection();

        try(PreparedStatement preparedStatement = conn.prepareStatement(UPDATE)) {

            preparedStatement.setString(1, name);
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
