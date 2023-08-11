package dev.thesemicolon.orderservice.repositories.impl;

import dev.thesemicolon.orderservice.repositories.ProductClientRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import the.semicolon.productservice.client.ApiClient;
import the.semicolon.productservice.client.api.ProductApi;
import the.semicolon.productservice.client.model.ProductDetailResponse;

@Repository
public class ProductClientRepositoryImpl implements ProductClientRepository {
  @Value("${services.product}")
  private String productServiceBasePath;
  private ProductApi productApi;

  public ProductClientRepositoryImpl(ProductApi productApi) {
    this.productApi = productApi;
  }

  @Override
  public Optional<ProductDetailResponse> getProductDetail(String productId) {
//    ApiClient apiClient = new ApiClient();
//    apiClient.setBearerToken("jklkmklmlkdnasdlkasd");
//    apiClient.setBasePath(this.productServiceBasePath);
//    this.productApi.setApiClient(apiClient);
    return Optional.of(this.productApi.getProductDetail(productId));
  }
}
