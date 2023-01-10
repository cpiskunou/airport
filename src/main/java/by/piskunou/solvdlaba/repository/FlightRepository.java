package by.piskunou.solvdlaba.repository;

import by.piskunou.solvdlaba.domain.Country;
import by.piskunou.solvdlaba.domain.Flight;
import by.piskunou.solvdlaba.repository.config.DataSourceConfig;
import by.piskunou.solvdlaba.repository.mapper.CountryMapper;
import by.piskunou.solvdlaba.web.dto.FlightRequest;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FlightRepository {
    private static final Logger logger = LogManager.getLogger();

    private final DataSourceConfig config;

    private static final String SCHEMA = "piskunou";

    public Optional<Flight> findById(long id) {
        return Optional.empty();
    }

    public List<Flight> search(FlightRequest flightRequest) {
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

            return null;
        } catch (SQLException e) {
            logger.warn("SQLException: Didn't find a country with cities by id");
        }
        return Collections.emptyList();
    }
}
