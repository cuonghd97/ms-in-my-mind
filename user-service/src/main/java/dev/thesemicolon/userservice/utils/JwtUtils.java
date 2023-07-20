package dev.thesemicolon.userservice.utils;

import dev.thesemicolon.userservice.daos.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${userService.app.jwtSecret}")
    private String jwtSecret;

    @Value("${userService.app.jwtExpirationMS}")
    private long jwtExpirationMS;

    @Value("${userService.app.jwtCookieName}")
    private String jwtCookieName;

    public String getJwtFromHeader(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    public String generateJwtToken(User user) {
        Date now = new Date();
        Date expirationTime = new Date(now.getTime() + this.jwtExpirationMS);
        return Jwts.builder()
                .setSubject(user.getId().toString())
                .setIssuedAt(now)
                .setExpiration(expirationTime)
                .signWith(this.getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(this.getKey()).build().parse(authToken);
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

    public Long getUserIdFromToken(String token) {
        return Long.valueOf(Jwts.parserBuilder().setSigningKey(this.getKey()).build()
                .parseClaimsJws(token).getBody().getSubject());
    }

    private Key getKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(this.jwtSecret));
    }
}
