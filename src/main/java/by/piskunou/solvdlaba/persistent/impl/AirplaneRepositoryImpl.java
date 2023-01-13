package by.piskunou.solvdlaba.persistent.impl;

import by.piskunou.solvdlaba.domain.Airplane;
import by.piskunou.solvdlaba.persistent.AirplaneRepository;
import by.piskunou.solvdlaba.persistent.config.DataSourceConfig;
import by.piskunou.solvdlaba.persistent.impl.mapper.AirplaneMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class AirplaneRepositoryImpl implements AirplaneRepository {

    private final DataSourceConfig config;
    private final AirplaneMapper mapper;

    @Override
    @SneakyThrows
    public void create(Airplane airplane) {
        Connection conn = config.getConnection();

        try(PreparedStatement preparedStatement =
                    conn.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, airplane.getModel());
            preparedStatement.setByte(2, airplane.getSeatsInRow());
            preparedStatement.setShort(3, airplane.getRowNo());
            preparedStatement.executeUpdate();

            try(ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if(resultSet.next()) {

                    Long id = resultSet.getLong("id");

                    airplane.setId(id);
                }
            }
        }
    }

    @Override
    @SneakyThrows
    public Optional<Airplane> findById(long id) {
        Connection conn = config.getConnection();

        try(PreparedStatement preparedStatement = conn.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                    Airplane airplane = mapper.mapRow(resultSet, 4);

                    return Optional.of(airplane);
                }
                return Optional.empty();
            }
        }
    }

    @Override
    @SneakyThrows
    public Optional<Airplane> findByModel(String model) {
        Connection conn = config.getConnection();

        try(PreparedStatement preparedStatement = conn.prepareStatement(FIND_BY_MODEL)) {
            preparedStatement.setString(1, model);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                    Airplane airplane = mapper.mapRow(resultSet, 4);

                    return Optional.of(airplane);
                }
                return Optional.empty();
            }
        }
    }

    @Override
    @SneakyThrows
    public List<Airplane> findAll() {
        Connection conn = config.getConnection();

        try(PreparedStatement preparedStatement = conn.prepareStatement(FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery()) {

            List<Airplane> airplanes = new LinkedList<>();

            while (resultSet.next()) {
                Airplane airplane = mapper.mapRow(resultSet, 4);
                airplanes.add(airplane);
            }

            return airplanes;
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
