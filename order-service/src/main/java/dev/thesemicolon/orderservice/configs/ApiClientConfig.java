package dev.thesemicolon.orderservice.configs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestTemplate;
import the.semicolon.productservice.client.ApiClient;
import the.semicolon.productservice.client.api.ProductApi;

/** Api client config. */
@Configuration
public class ApiClientConfig {
  @Value("${services.product}")
  private String productServiceBasePath;

  @Autowired private ProductClientInterceptor productClientInterceptor;

  @Bean
  public ProductApi productApiClient() {
    RestTemplate restTemplate = new RestTemplate();
    List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
    interceptors.add(this.productClientInterceptor);
    restTemplate.setInterceptors(interceptors);

    ApiClient apiClient = new ApiClient(restTemplate).setBasePath(this.productServiceBasePath);

    return new ProductApi(apiClient);
  }
}
