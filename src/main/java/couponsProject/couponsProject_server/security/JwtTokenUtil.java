package couponsProject.couponsProject_server.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Component
public class JwtTokenUtil {
    @Value("${jwt.token.validity}")
    private int validitySeconds;
    private String secretKey;
    private Map<String, LocalDateTime> activeTokens = new HashMap<>();

    public JwtTokenUtil() {
        if (this.secretKey == null || this.secretKey.isEmpty()) {
            this.secretKey = generateSecretKey();
        }
        log.info("entering JwtTokenUtil secretKey:{} ",this.secretKey );
    }

    public String getSecretKey() {
        return secretKey;
    }

    private static String generateSecretKey() {
        log.info("entering generateSecretKey");

        // Generate a strong random key
        SecureRandom secureRandom = new SecureRandom();
        byte[] keyBytes = new byte[32]; // 256-bit key
        secureRandom.nextBytes(keyBytes);
        // Encode the key in Base64 for use as a JWT secret
         return Base64.getEncoder().encodeToString(keyBytes);
    }

    public String createToken(int id, String name, String email,String role) {
        log.info("entering createToken");

        return "Bearer " + JWT.create()
                .withIssuer("com.garbage.collectors")
                .withClaim("id",id)
                .withClaim("name", name)
                .withClaim("email", email)
                .withClaim("role", role)
                .sign(Algorithm.HMAC256(secretKey)); // Use HMAC256 for signing with a secret key
    }

    public boolean addToken(String token) {
        log.info("entering addToken token:{}", token);
            return activeTokens.put(token,LocalDateTime.now())==null;
    }

    public void updateLastActivity(String token) {
        log.info("entering updateLastActivity");
        if (activeTokens.containsKey(token)) {
            activeTokens.put(token, LocalDateTime.now());
        } else {
            System.out.println("Token not found in active tokens.");
        }
    }

    public void removeToken(String token) {
        log.info("entering removeToken");
        activeTokens.remove(token);
    }

    public boolean checkToken(String token) {
        log.info("entering checkToken");
        if (activeTokens.get(token).plusSeconds(validitySeconds).isAfter(LocalDateTime.now())) {
            updateLastActivity(token);
            return true;
        }
        log.error("token is outdated");
        return false;
    }

}