package by.piskunou.solvdlaba.persistent.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
@RequiredArgsConstructor
public class DataSourceConfig {

    private final DataSource dataSource;

    public Connection getConnection() throws SQLException {
        Connection conn = DataSourceUtils.getConnection(dataSource);
        conn.setSchema("piskunou");

        return conn;
    }

}
