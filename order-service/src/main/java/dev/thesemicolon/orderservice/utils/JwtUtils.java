package dev.thesemicolon.orderservice.utils;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import java.security.Key;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JwtUtils {
  private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtils.class);

  //    @Value("${productService.app.jwtSecret}")
  private static final String jwtSecret =
      "===========================Spring===========================";

  public static String getJwtFromHeader(HttpServletRequest request) {
    return request.getHeader("Authorization");
  }

  public static boolean validateJwtToken(String authToken) {
    try {
      Jwts.parserBuilder().setSigningKey(JwtUtils.getKey()).build().parse(authToken);
      return true;
    } catch (MalformedJwtException e) {
      LOGGER.error("Invalid JWT token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      LOGGER.error("JWT token is expired: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      LOGGER.error("JWT token is unsupported: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      LOGGER.error("JWT claims string is empty: {}", e.getMessage());
    }

    return false;
  }

  public static String getUserIdFromToken(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(JwtUtils.getKey())
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
  }

  private static Key getKey() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(JwtUtils.jwtSecret));
  }
}
