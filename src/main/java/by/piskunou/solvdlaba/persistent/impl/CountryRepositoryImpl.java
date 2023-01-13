package by.piskunou.solvdlaba.persistent.impl;

import by.piskunou.solvdlaba.domain.Country;
import by.piskunou.solvdlaba.persistent.CountryRepository;
import by.piskunou.solvdlaba.persistent.config.DataSourceConfig;
import by.piskunou.solvdlaba.persistent.impl.mapper.CountryMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CountryRepositoryImpl implements CountryRepository {

    private final DataSourceConfig config;
    private final CountryMapper countryMapper;

    @Override
    @SneakyThrows
    public List<Country> findAll() {
        Connection conn = config.getConnection();

        try (PreparedStatement preparedStatement = conn.prepareStatement(FIND_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            List<Country> countries = new LinkedList<>();

            while (resultSet.next()) {
                Country country = countryMapper.mapRow(resultSet, 1);
                countries.add(country);
            }

            return countries;
        }
    }

    @Override
    @SneakyThrows
    public Optional<Country> findById(long id) {
        Connection conn = config.getConnection();

        try (PreparedStatement preparedStatement = conn.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Country country = countryMapper.mapRow(resultSet, 1);

                    return Optional.of(country);
                }
                return Optional.empty();
            }
        }
    }

    @Override
    @SneakyThrows
    public Optional<Country> findByName(String name) {
        Connection conn = config.getConnection();

        try (PreparedStatement preparedStatement = conn.prepareStatement(FIND_BY_NAME)) {
            preparedStatement.setString(1, name);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                    Country country = countryMapper.mapRow(resultSet, 2);

                    return Optional.of(country);
                }
                return Optional.empty();
            }
        }
    }

    @Override
    @SneakyThrows
    public Optional<Country> findCountryCities(long id) {
        Connection conn = config.getConnection();

        try(PreparedStatement preparedStatement = conn.prepareStatement(FIND_CITIES_BY_ID,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY)) {
            preparedStatement.setLong(1, id);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                    Country country = countryMapper.citiesMapRow(resultSet);

                    return Optional.of(country);
                }
                return Optional.empty();
            }
        }
    }

    @Override
    @SneakyThrows
    public Optional<Country> findCountryAirports(long id) {
        Connection conn = config.getConnection();

        try(PreparedStatement preparedStatement = conn.prepareStatement(FIND_AIRPORTS_BY_ID,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY)) {

            preparedStatement.setLong(1, id);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                    Country country = countryMapper.airportsMapRow(resultSet);

                    return Optional.of(country);
                }
                return Optional.empty();
            }
        }
    }

    @Override
    @SneakyThrows
    public void create(Country country) {
        Connection conn = config.getConnection();

        try(PreparedStatement preparedStatement =
                    conn.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, country.getName());
            preparedStatement.executeUpdate();

            try(ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if(resultSet.next()) {
                    Long id = resultSet.getLong("id");

                    country.setId(id);
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
