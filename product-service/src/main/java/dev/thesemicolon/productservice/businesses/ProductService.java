package dev.thesemicolon.productservice.businesses;

import dev.thesemicolon.productservice.commons.models.CreateProductObject;
import the.semicolon.productservice.server.model.CreateProductResponse;
import the.semicolon.productservice.server.model.DeleteProductResponse;
import the.semicolon.productservice.server.model.ProductDetailResponse;
import the.semicolon.productservice.server.model.UpdateProductRequest;

public interface ProductService {
    CreateProductResponse createProduct(CreateProductObject createProductObject) throws Exception;

    UpdateProductRequest updateProduct(String productId, UpdateProductRequest updateProductRequest);

    ProductDetailResponse getProductDetail(String productId) throws Exception;

    DeleteProductResponse deleteProduct(String productId);
}
