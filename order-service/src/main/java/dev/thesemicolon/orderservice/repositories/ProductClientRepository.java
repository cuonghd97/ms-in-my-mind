package dev.thesemicolon.orderservice.repositories;

import java.util.Optional;
import the.semicolon.productservice.client.model.ProductDetailResponse;

public interface ProductClientRepository {
  Optional<ProductDetailResponse> getProductDetail(String productId);
}
