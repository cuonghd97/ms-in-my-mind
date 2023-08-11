package dev.thesemicolon.productservice.businesses;

import java.io.InputStream;

public interface MinioService {
    String getPresignedUrl(String fileName) throws Exception;

    void uploadFile(String fileName, InputStream fileInputStream, String contentType) throws Exception;

    void removeFile(String fileName) throws Exception;
}
