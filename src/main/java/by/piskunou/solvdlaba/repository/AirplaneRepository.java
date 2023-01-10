package by.piskunou.solvdlaba.repository;

import by.piskunou.solvdlaba.domain.Airplane;
import by.piskunou.solvdlaba.repository.config.DataSourceConfig;
import by.piskunou.solvdlaba.repository.mapper.AirplaneMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class AirplaneRepository {

    private static final Logger logger = LogManager.getLogger();

    private final DataSourceConfig config;

    private final AirplaneMapper mapper;

    private static final String SCHEMA = "piskunou";

    public Optional<Airplane> create(Airplane airplane) {
        try {
            Connection conn = config.getConnection();
            conn.setSchema(SCHEMA);
            PreparedStatement preparedStatement =
                    conn.prepareStatement("insert into airplane(username, row_seat_no, row_no) values(?, ?, ?)",
                            Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, airplane.getModel());
            preparedStatement.setByte(2, airplane.getRowSeatNo());
            preparedStatement.setShort(3, airplane.getRowNo());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();

            airplane.setId(resultSet.getLong("id"));

            return Optional.of(airplane);
        } catch (SQLException e) {
            logger.error("SQLException: Didn't add");
        }
        return Optional.empty();
    }

    public Optional<Airplane> findById(long id) {
        try {
            Connection conn = config.getConnection();
            conn.setSchema(SCHEMA);
            PreparedStatement preparedStatement =
                    conn.prepareStatement("select id, model, row_seat_no, row_no from airplane where id = ?");

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            Airplane airplane = mapper.mapRow(resultSet, 4);

            return Optional.of(airplane);
        } catch (SQLException e) {
            logger.warn("SQLException: Didn't find by id");
        }
        return Optional.empty();
    }

    public Optional<Airplane> findByModel(String model) {
        try {
            Connection conn = config.getConnection();
            conn.setSchema(SCHEMA);
            PreparedStatement preparedStatement =
                    conn.prepareStatement("select id, model, row_seat_no, row_no from airplane where model = ?");

            preparedStatement.setString(1, model);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            Airplane airplane = mapper.mapRow(resultSet, 4);

            return Optional.of(airplane);
        } catch (SQLException e) {
            logger.warn("SQLException: Didn't find by model");
        }
        return Optional.empty();
    }

    public List<Airplane> findAll() {
        try {
            Connection conn = config.getConnection();
            conn.setSchema(SCHEMA);
            PreparedStatement preparedStatement =
                    conn.prepareStatement("select id, model, row_seat_no, row_no from airplane");

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Airplane> airplanes = new LinkedList<>();

            while(resultSet.next()) {
                Airplane airplane = mapper.mapRow(resultSet, 4);
                airplanes.add(airplane);
            }

            return airplanes;
        } catch (SQLException e) {
            logger.warn("SQLException: Didn't find all");
        }
        return Collections.emptyList();
    }

    public void removeById(long id) {
        try {
            Connection conn = config.getConnection();
            conn.setSchema(SCHEMA);
            PreparedStatement preparedStatement =
                    conn.prepareStatement("delete from airplane where id = ?");

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQLException: Didn't remove by id");
        }
    }

    public void removeByModel(String model) {
        try {
            Connection conn = config.getConnection();
            conn.setSchema(SCHEMA);
            PreparedStatement preparedStatement =
                    conn.prepareStatement("delete from airplane where model = ?");

            preparedStatement.setString(1, model);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQLException: Didn't remove by model");
        }
    }
}
