package dev.thesemicolon.productservice.businesses.impl;

import dev.thesemicolon.productservice.businesses.ProductService;
import dev.thesemicolon.productservice.daos.Product;
import dev.thesemicolon.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import the.semicolon.productservice.server.model.*;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public CreateProductResponse createProduct(CreateProductRequest createProductRequest) {
        Product product = new Product();
        product.setName(createProductRequest.getName());
        product.setPrice(createProductRequest.getPrice());

        return null;
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
