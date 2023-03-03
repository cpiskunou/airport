package by.piskunou.solvdlaba.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "minio")
@Getter
@Setter
public class MinioProperties {

    private String bucket;
    private String url;
    private String username;
    private String password;

}
