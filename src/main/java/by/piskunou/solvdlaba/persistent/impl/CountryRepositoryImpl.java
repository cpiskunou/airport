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

    private static final String FIND_BY_ID = """
            select id as country_id,
                   name as country_name
            from country where id = ?""";

    private static final String FIND_BY_NAME = """
            select id as country_id,
                   name as country_name
            from country where name = ?""";

    private static final String FIND_CITIES_BY_ID = """
        select country.id as country_id,
               country.name as country_name,
               city.id as city_id,
               city.name as city_name
        from country inner join city on country.id = city.fk_country_id 
        where country.id = ?""";

    private static final String FIND_AIRPORTS_BY_ID = """
        select country.id as country_id,
               country.name as country_name,
               city.id as city_id,
               city.name as city_name,
               airport.id as airport_id,
               airport.name as airport_name
        from country inner join city on country.id = city.fk_country_id
                     inner join airport on city.id = airport.fk_city_id
        where country.id = ?""";

    private static final String FIND_ALL = """
            select id as country_id,
                   name as country_name
            from country""";

    private static final String CREATE = "insert into country(name) values(?)";
    private static final String UPDATE = "update country set name = ? where id = ?";
    private static final String DELETE = "delete from country where id = ?";

    @Override
    @SneakyThrows
    public List<Country> findAll() {
        Connection conn = config.getConnection();

        try (PreparedStatement preparedStatement = conn.prepareStatement(FIND_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            List<Country> countries = new LinkedList<>();

            while (resultSet.next()) {
                Country country = countryMapper.mapRow(resultSet);
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
                    Country country = countryMapper.mapRow(resultSet);

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
                    Country country = countryMapper.mapRow(resultSet);

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
