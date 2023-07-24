package dev.thesemicolon.productservice.businesses;

import the.semicolon.productservice.server.model.*;

public interface ProductService {
    CreateProductResponse createProduct(CreateProductRequest createProductRequest);

    UpdateProductRequest updateProduct(UpdateProductRequest updateProductRequest);

    ProductDetailResponse getProductDetail(Long productId);

    DeleteProductResponse deleteProduct(Long productId);
}
