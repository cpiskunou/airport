package by.piskunou.solvdlaba.persistence;

import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import okhttp3.HttpUrl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
@RequiredArgsConstructor
public class MinioConfig {

	private final MinioProperties minioProperties;

	@Bean
	public MinioClient minioClient() {
		return MinioClient.builder()
				.endpoint( Objects.requireNonNull(HttpUrl.parse(minioProperties.getUrl())) )
				.credentials(minioProperties.getUsername(), minioProperties.getPassword())
				.build();
	}

}
