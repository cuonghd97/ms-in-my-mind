package dev.thesemicolon.productservice.businesses;

import dev.thesemicolon.productservice.commons.exceptions.BusinessException;
import dev.thesemicolon.productservice.commons.models.CreateProductObject;
import dev.thesemicolon.productservice.commons.models.UpdateProductObject;
import the.semicolon.productservice.server.model.CreateProductResponse;
import the.semicolon.productservice.server.model.DeleteProductResponse;
import the.semicolon.productservice.server.model.ProductDetailResponse;
import the.semicolon.productservice.server.model.UpdateProductResponse;

public interface ProductService {
    CreateProductResponse createProduct(CreateProductObject createProductObject) throws Exception;

    UpdateProductResponse updateProduct(String productId, UpdateProductObject updateProductObject) throws Exception;

    ProductDetailResponse getProductDetail(String productId) throws Exception;

    DeleteProductResponse deleteProduct(String productId) throws Exception;
}
