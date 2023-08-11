package dev.thesemicolon.orderservice.configs;

import dev.thesemicolon.orderservice.commons.models.UserInfo;
import java.io.IOException;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ProductClientInterceptor implements ClientHttpRequestInterceptor {

  @Override
  public ClientHttpResponse intercept(
      HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
    UserInfo userInfo =
        (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    request.getHeaders().add("Authorization", userInfo.getToken());
    ClientHttpResponse response = execution.execute(request, body);
    return response;
  }
}
