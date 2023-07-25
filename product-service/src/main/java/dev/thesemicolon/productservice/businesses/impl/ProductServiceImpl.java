package dev.thesemicolon.productservice.businesses.impl;

import dev.thesemicolon.productservice.businesses.MinioService;
import dev.thesemicolon.productservice.businesses.ProductService;
import dev.thesemicolon.productservice.daos.Product;
import dev.thesemicolon.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import the.semicolon.productservice.server.model.*;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private MinioService minioService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, MinioService minioService) {
        this.productRepository = productRepository;
        this.minioService = minioService;
    }

    @Override
    public CreateProductResponse createProduct(CreateProductRequest createProductRequest) {
        Product product = new Product();
        product.setName(createProductRequest.getName());
        product.setPrice(createProductRequest.getPrice().longValue());
        product.setDescription(createProductRequest.getDescription());
        product.setImage(createProductRequest.getImage().getFilename());

        this.productRepository.save(product);
        return new CreateProductResponse().message("Create product success");
    }

    @Override
    public UpdateProductRequest updateProduct(UpdateProductRequest updateProductRequest) {
        return null;
    }

    @Override
    public ProductDetailResponse getProductDetail(Long productId) {
        return null;
    }

    @Override
    public DeleteProductResponse deleteProduct(Long productId) {
        return null;
    }
}
