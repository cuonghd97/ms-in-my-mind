package dev.thesemicolon.productservice.businesses.impl;

import dev.thesemicolon.productservice.businesses.MinioService;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class MinioServiceImpl implements MinioService {
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
    public String getPresignedUrl(String fileName) throws Exception{
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
    public void uploadFile(String fileName, byte[] content) throws Exception {

    }


}
