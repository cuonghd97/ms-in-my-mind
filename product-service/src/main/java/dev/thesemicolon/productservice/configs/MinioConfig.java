package dev.thesemicolon.productservice.configs;


import io.minio.MinioClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(MinioConfig.class);

    @Value("${minio.accessKey}")
    private String accessKey;

    @Value("${minio.secretKey}")
    private String secretKey;

    @Value("${minio.url}")
    private String minioUrl;

    @Bean
    public MinioClient minioClient() {
        try {
            return MinioClient.builder()
                    .endpoint("127.0.0.1", 9000, false)
                    .credentials(this.accessKey, this.secretKey)
                    .build();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }
}
