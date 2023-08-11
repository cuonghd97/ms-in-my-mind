package dev.thesemicolon.orderservice.apis;

import dev.thesemicolon.orderservice.commons.models.UserInfo;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseController {
  protected UserInfo getUserInfo() throws Exception {
    return (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }
}
