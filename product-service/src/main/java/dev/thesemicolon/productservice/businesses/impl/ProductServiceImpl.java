package dev.thesemicolon.productservice.businesses.impl;

import dev.thesemicolon.productservice.businesses.MinioService;
import dev.thesemicolon.productservice.businesses.ProductService;
import dev.thesemicolon.productservice.commons.exceptions.BusinessException;
import dev.thesemicolon.productservice.commons.models.CreateProductObject;
import dev.thesemicolon.productservice.commons.models.UpdateProductObject;
import dev.thesemicolon.productservice.daos.Product;
import dev.thesemicolon.productservice.repositories.ProductRepository;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import the.semicolon.productservice.server.model.CreateProductResponse;
import the.semicolon.productservice.server.model.DeleteProductResponse;
import the.semicolon.productservice.server.model.ProductDetailResponse;
import the.semicolon.productservice.server.model.UpdateProductResponse;

import java.math.BigDecimal;
import java.time.ZoneOffset;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
    private ProductRepository productRepository;
    private MinioService minioService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, MinioService minioService) {
        this.productRepository = productRepository;
        this.minioService = minioService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CreateProductResponse createProduct(CreateProductObject createProductObject) throws Exception {
        UUID uuid = UUID.randomUUID();
        String fileName = buildFileName(uuid.toString(), FilenameUtils.getExtension(createProductObject.getImage().getOriginalFilename()));

        Product product = new Product();
        product.setId(uuid.toString());
        product.setName(createProductObject.getName());
        product.setPrice(createProductObject.getPrice().longValue());
        product.setDescription(createProductObject.getDescription());
        product.setQuantity(createProductObject.getQuantity());
        product.setImage(fileName);

        this.productRepository.save(product);
        this.minioService.uploadFile(
                fileName,
                createProductObject.getImage().getInputStream(),
                createProductObject.getImage().getContentType()
        );
        LOGGER.info("Create product success");
        return new CreateProductResponse().message("Create product success");
    }

    @Override
    public UpdateProductResponse updateProduct(String productId, UpdateProductObject updateProductObject) throws Exception {
        String fileName = buildFileName(productId, FilenameUtils.getExtension(updateProductObject.getImage().getOriginalFilename()));

        Product product = this.productRepository.findById(productId)
                .orElseThrow(() -> new BusinessException("Product not found"));
        product.setImage(fileName);
        product.setName(updateProductObject.getName());
        product.setPrice(updateProductObject.getPrice().longValue());
        product.setDescription(updateProductObject.getDescription());
        product.setQuantity(updateProductObject.getQuantity());

        this.productRepository.save(product);
        this.minioService.uploadFile(
                fileName,
                updateProductObject.getImage().getInputStream(),
                updateProductObject.getImage().getContentType()
        );
        LOGGER.info("Update product success");
        return new UpdateProductResponse().message("Update product success");
    }


    @Override
    public ProductDetailResponse getProductDetail(String productId) throws Exception {
        Product product = this.productRepository.findById(productId)
                .orElseThrow(() -> new BusinessException("product not found"));
        String imagePresignedUrl = this.minioService.getPresignedUrl(product.getImage());

        return new ProductDetailResponse()
                .name(product.getName())
                .price(BigDecimal.valueOf(product.getPrice()))
                .description(product.getDescription())
                .quantity(product.getQuantity())
                .createdAt((int) product.getCreatedAt().toEpochSecond(ZoneOffset.UTC))
                .imageUrl(imagePresignedUrl);
    }

    @Override
    @Transactional
    public DeleteProductResponse deleteProduct(String productId) throws Exception {
        Product product = this.productRepository
                .findById(productId)
                .orElseThrow(() -> new BusinessException("Product not found"));
        this.productRepository.delete(product);
        this.minioService.removeFile(product.getImage());
        return new DeleteProductResponse().message("Delete product success");
    }

    private static String buildFileName(String productId, String extension) {
        return new StringBuilder().append(productId).append(".").append(extension).toString();
    }
}
