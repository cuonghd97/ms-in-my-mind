package dev.thesemicolon.productservice.businesses;

public interface MinioService {
    String getPresignedUrl(String fileName) throws Exception;

    void uploadFile(String fileName, byte[] content) throws Exception;
}
