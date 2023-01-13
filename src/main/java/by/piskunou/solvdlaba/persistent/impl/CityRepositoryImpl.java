package by.piskunou.solvdlaba.persistent.impl;

import by.piskunou.solvdlaba.domain.City;
import by.piskunou.solvdlaba.persistent.CityRepository;
import by.piskunou.solvdlaba.DataSourceConfig;
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

    private static final String FIND_BY_ID = """
             select id as city_id,
                    name as city_name
             from city where id = ?""";

    private static final String FIND_BY_NAME = """
             select id as city_id,
                    name as city_name
             from city where name = ?""";

    private static final String FIND_AIRPORTS_BY_ID = """
            select city.id as city_id,
                   city.name as city_name,
                   airport.id as airport_id,
                   airport.name as airport_name
            from city inner join airport on city.id = airport.fk_city_id 
            where city.id = ?""";

    private static final String FIND_ALL = """
             select id as city_id,
                    name as city_name
             from city""";

    private static final String CREATE = "insert into city(fk_country_id, name) values(?, ?)";
    private static final String UPDATE = "update city set name = ? where id = ?";
    private static final String DELETE = "delete from city where id = ?";
    private static final String EXISTS_BY_ID = "select exists (select from city where id= ?)";
    private static final String EXISTS_BY_NAME = "select exists (select from city where name= ?)";

    @Override
    @SneakyThrows
    public List<City> findAll() {
        Connection conn = config.getConnection();

        try (PreparedStatement preparedStatement = conn.prepareStatement(FIND_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            List<City> cities = new LinkedList<>();

            while (resultSet.next()) {
                City city = cityMapper.mapRow(resultSet);
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
                City city =  null;
                if (resultSet.next()) {
                    city = cityMapper.mapRow(resultSet);
                }
                return Optional.ofNullable(city);
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
                City city = null;
                if (resultSet.next()) {
                    city = cityMapper.airportsMapRow(resultSet);
                }
                return Optional.ofNullable(city);
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
                City city = null;
                if(resultSet.next()) {
                    city = cityMapper.mapRow(resultSet);
                }
                return Optional.ofNullable(city);
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

    @Override
    @SneakyThrows
    public boolean isExists(long id) {
        Connection conn = config.getConnection();

        try (PreparedStatement preparedStatement = conn.prepareStatement(EXISTS_BY_ID)) {
            preparedStatement.setLong(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return resultSet.getBoolean("exists");
            }
        }
    }

    @Override
    @SneakyThrows
    public boolean isExists(String name) {
        Connection conn = config.getConnection();

        try (PreparedStatement preparedStatement = conn.prepareStatement(EXISTS_BY_NAME)) {
            preparedStatement.setString(1, name);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return resultSet.getBoolean("exists");
            }
        }
    }

}
