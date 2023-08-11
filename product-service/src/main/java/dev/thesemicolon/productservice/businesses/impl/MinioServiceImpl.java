package dev.thesemicolon.productservice.businesses.impl;

import dev.thesemicolon.productservice.businesses.MinioService;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.http.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

@Service
public class MinioServiceImpl implements MinioService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MinioServiceImpl.class);
    private MinioClient minioClient;

    @Value("${minio.bucketName}")
    private String bucketName;

    @Value("${minio.defaultFolder}")
    private String defaultFolder;

    @Autowired
    public MinioServiceImpl(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Override
    public String getPresignedUrl(String fileName) throws Exception {
        return this.minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(this.bucketName)
                        .object(fileName)
                        .expiry(2, TimeUnit.DAYS)
                        .build()
        );
    }

    @Override
    public void uploadFile(String fileName, InputStream fileInputStream, String contentType) throws Exception {
        try {
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(this.bucketName)
                    .object(fileName)
                    .stream(fileInputStream, fileInputStream.available(), -1)
                    .contentType(contentType)
                    .build());
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public void removeFile(String fileName) throws Exception {
        minioClient.removeObject(RemoveObjectArgs.builder()
                .bucket(this.bucketName)
                .object(fileName)
                .build()
        );
    }
}
