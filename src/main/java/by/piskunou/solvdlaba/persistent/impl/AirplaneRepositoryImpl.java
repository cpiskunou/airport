package by.piskunou.solvdlaba.persistent.impl;

import by.piskunou.solvdlaba.domain.Airplane;
import by.piskunou.solvdlaba.persistent.AirplaneRepository;
import by.piskunou.solvdlaba.persistent.config.DataSourceConfig;
import by.piskunou.solvdlaba.persistent.impl.mapper.AirplaneMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;

@Repository
@RequiredArgsConstructor
@Slf4j
public class AirplaneRepositoryImpl implements AirplaneRepository {

    private final DataSourceConfig config;
    private final AirplaneMapper mapper;

    @Override
    public Optional<Long> create(Airplane airplane) {
        try {
            Connection conn = config.getConnection();

            try(PreparedStatement preparedStatement =
                    conn.prepareStatement("insert into airplane(username, row_seat_no, row_no) values(?, ?, ?)",
                            Statement.RETURN_GENERATED_KEYS)) {

                preparedStatement.setString(1, airplane.getModel());
                preparedStatement.setByte(2, airplane.getSeatsInRow());
                preparedStatement.setShort(3, airplane.getRowNo());
                preparedStatement.executeUpdate();

                try(ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    resultSet.next();

                    Long id = resultSet.getLong("id");

                    return Optional.of(id);
                }
            }
        } catch (SQLException e) {
            log.error("SQLException: Didn't add");
        }
        return Optional.empty();
    }

    @Override
    public Optional<Airplane> findById(long id) {
        try {
            Connection conn = config.getConnection();

            try(PreparedStatement preparedStatement =
                    conn.prepareStatement("""
                            select id as airplane_id,
                                   model as airplane_model,
                                   seats_in_row as airplane_seats_in_row,
                                   row_no as airplane_row_no
                            from airplane where id = ?""")) {

                preparedStatement.setLong(1, id);

                try(ResultSet resultSet = preparedStatement.executeQuery()) {
                    resultSet.next();

                    Airplane airplane = mapper.mapRow(resultSet, 4);

                    return Optional.of(airplane);
                }
            }
        } catch (SQLException e) {
            log.warn("SQLException: Didn't find by id");
        }
        return Optional.empty();
    }

    @Override
    public Optional<Airplane> findByModel(String model) {
        try {
            Connection conn = config.getConnection();

            try(PreparedStatement preparedStatement =
                    conn.prepareStatement("""
                            select id as airplane_id,
                                   model as airplane_model,
                                   seats_in_row as airplane_seats_in_row,
                                   row_no as airplane_row_no
                            from airplane where model = ?""")) {

                preparedStatement.setString(1, model);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    resultSet.next();

                    Airplane airplane = mapper.mapRow(resultSet, 4);

                    return Optional.of(airplane);
                }
            }
        } catch (SQLException e) {
            log.warn("SQLException: Didn't find by model");
        }
        return Optional.empty();
    }

    @Override
    public List<Airplane> findAll() {
        try {
            Connection conn = config.getConnection();

            try(PreparedStatement preparedStatement =
                    conn.prepareStatement("""
                            select id as airplane_id,
                                   model as airplane_model,
                                   seats_in_row as airplane_seats_in_row,
                                   row_no as airplane_row_no
                            from airplane""")) {

                try(ResultSet resultSet = preparedStatement.executeQuery()) {

                    List<Airplane> airplanes = new LinkedList<>();

                    while (resultSet.next()) {
                        Airplane airplane = mapper.mapRow(resultSet, 4);
                        airplanes.add(airplane);
                    }

                    return airplanes;
                }
            }
        } catch (SQLException e) {
            log.warn("SQLException: Didn't find all");
        }
        return Collections.emptyList();
    }

    @Override
    public void removeById(long id) {
        try {
            Connection conn = config.getConnection();

            try(PreparedStatement preparedStatement =
                    conn.prepareStatement("delete from airplane where id = ?")) {

                preparedStatement.setLong(1, id);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            log.error("SQLException: Didn't remove by id");
        }
    }

    @Override
    public void removeByModel(String model) {
        try {
            Connection conn = config.getConnection();

            try(PreparedStatement preparedStatement =
                    conn.prepareStatement("delete from airplane where model = ?")) {

                preparedStatement.setString(1, model);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            log.error("SQLException: Didn't remove by model");
        }
    }
}
