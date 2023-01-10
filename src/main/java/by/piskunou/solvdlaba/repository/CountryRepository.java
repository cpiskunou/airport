package by.piskunou.solvdlaba.repository;

import by.piskunou.solvdlaba.domain.Country;
import by.piskunou.solvdlaba.repository.config.DataSourceConfig;
import by.piskunou.solvdlaba.repository.mapper.CountryMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CountryRepository {

    private static final Logger logger = LogManager.getLogger();

    private final DataSourceConfig config;

    private final CountryMapper countryMapper;

    private static final String SCHEMA = "piskunou";

    public List<Country> findAll() {
        try {
            Connection conn = config.getConnection();
            conn.setSchema(SCHEMA);
            PreparedStatement preparedStatement =
                    conn.prepareStatement("""
                            select id as country_id,
                            name as country_name
                            from country""");

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Country> countries = new LinkedList<>();

            while(resultSet.next()) {
                Country country = countryMapper.mapRow(resultSet, 1);
                countries.add(country);
            }

            return countries;
        } catch (SQLException e) {
            logger.warn("SQLException: Didn't find all counties");
        }
        return Collections.emptyList();
    }

    public Optional<Country> findById(long id) {
        try {
            Connection conn = config.getConnection();
            conn.setSchema(SCHEMA);
            PreparedStatement preparedStatement =
                    conn.prepareStatement("""
                            select id as country_id,
                            name as country_name
                            from country where id = ?""");

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            Country country = countryMapper.mapRow(resultSet, 1);

            return Optional.of(country);
        } catch (SQLException e) {
            logger.warn("SQLException: Didn't find a country by id");
        }
        return Optional.empty();
    }

    public Optional<Country> findByName(String name) {
        try {
            Connection conn = config.getConnection();
            conn.setSchema(SCHEMA);
            PreparedStatement preparedStatement =
                    conn.prepareStatement("""
                            select id as country_id,
                            name as country_name
                            from country where name = ?""");

            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            Country country = countryMapper.mapRow(resultSet, 2);

            return Optional.of(country);
        } catch (SQLException e) {
            logger.warn("SQLException: Didn't find a country by name");
        }
        return Optional.empty();
    }

    public Optional<Country> findCountryCities(long id) {
        try {
            Connection conn = config.getConnection();
            conn.setSchema(SCHEMA);
            PreparedStatement preparedStatement = conn.prepareStatement("""
                        select country.id as country_id,
                               country.name as country_name,
                               city.id as city_id,
                               city.name as city_name
                        from country join city on country.id = city.fk_country_id 
                        where country.id = ?""",
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            Country country = countryMapper.citiesMapRow(resultSet);

            return Optional.of(country);
        } catch (SQLException e) {
            logger.warn("SQLException: Didn't find a country with cities by id");
        }
        return Optional.empty();
    }

    public Optional<Country> findCountryAirports(long id) {
        try {
            Connection conn = config.getConnection();
            conn.setSchema(SCHEMA);
            PreparedStatement preparedStatement = conn.prepareStatement("""
                        select country.id as country_id,
                               country.name as country_name,
                               city.id as city_id,
                               city.name as city_name,
                               airport.id as airport_id,
                               airport.name as airport_name
                        from country join city on country.id = city.fk_country_id
                                     join airport on city.id = airport.fk_city_id
                        where country.id = ?""",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            Country country = countryMapper.airportsMapRow(resultSet);

            return Optional.of(country);
        } catch (SQLException e) {
            logger.warn("SQLException: Didn't find a country with cities by id");
        }
        return Optional.empty();
    }

    public Optional<Country> create(Country country) {
        try {
            Connection conn = config.getConnection();
            conn.setSchema(SCHEMA);
            PreparedStatement preparedStatement =
                    conn.prepareStatement("insert into country(name) values(?)",
                            Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, country.getName());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();

            country.setId(resultSet.getLong("id"));

            return Optional.of(country);
        } catch (SQLException e) {
            logger.error("SQLException: Didn't create a country");
        }
        return Optional.empty();
    }

    public Optional<Country> updateNameById(long id, String name) {
        try {
            Connection conn = config.getConnection();
            conn.setSchema(SCHEMA);
            PreparedStatement preparedStatement =
                    conn.prepareStatement("update country set name = ? where id = ?",
                            Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, id);

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();

            Country country = new Country(resultSet.getLong("id"),
                                        resultSet.getString("name"));

            return Optional.of(country);
        } catch (SQLException e) {
            logger.warn("SQLException: Didn't update a country");
        }
        return Optional.empty();
    }

    public void removeById(long id) {
        try {
            Connection conn = config.getConnection();
            conn.setSchema(SCHEMA);
            PreparedStatement preparedStatement =
                    conn.prepareStatement("delete from country where id = ?");

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQLException: Didn't remove a country by id");
        }
    }

    public void removeByName(String name) {
        try {
            Connection conn = config.getConnection();
            conn.setSchema(SCHEMA);
            PreparedStatement preparedStatement =
                    conn.prepareStatement("delete from country where \"name\" = ?");

            preparedStatement.setString(1, name);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQLException: Didn't remove a country by name");
        }
    }
}
