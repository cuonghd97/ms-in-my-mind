package dev.thesemicolon.orderservice.configs;

import dev.thesemicolon.orderservice.commons.models.UserInfo;
import dev.thesemicolon.orderservice.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

public class AuthTokenFilter extends OncePerRequestFilter {
  private static final Logger LOGGER = LoggerFactory.getLogger(AuthTokenFilter.class);

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {
      String jwt = this.parseJwt(request);
      if (jwt != null && JwtUtils.validateJwtToken(jwt)) {
        String userId = JwtUtils.getUserIdFromToken(jwt);
        UserInfo userInfo = new UserInfo(userId, jwt);
        UsernamePasswordAuthenticationToken authentication =
            new UsernamePasswordAuthenticationToken(userInfo, null, Arrays.asList());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    } catch (Exception e) {
      LOGGER.error("Cannot set user authentication: {}", e);
    }

    filterChain.doFilter(request, response);
  }

  private String parseJwt(HttpServletRequest request) {
    return JwtUtils.getJwtFromHeader(request);
  }
}
