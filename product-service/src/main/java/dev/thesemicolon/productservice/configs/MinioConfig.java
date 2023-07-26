package dev.thesemicolon.productservice.configs;


import io.minio.MinioClient;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class MinioConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(MinioConfig.class);

    @Value("${minio.accessKey}")
    private String accessKey;

    @Value("${minio.secretKey}")
    private String secretKey;

    @Value("${minio.host}")
    private String host;

    @Value("${minio.port}")
    private int port;

    @Bean
    public MinioClient minioClient() {
        try {
            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.MINUTES)
                    .writeTimeout(10, TimeUnit.MINUTES)
                    .readTimeout(30, TimeUnit.MINUTES)
                    .build();
            return MinioClient.builder()
                    .endpoint(this.host, this.port, false)
                    .httpClient(httpClient)
                    .credentials(this.accessKey, this.secretKey)
                    .build();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }
}
