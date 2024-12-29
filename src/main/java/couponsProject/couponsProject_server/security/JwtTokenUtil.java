package couponsProject.couponsProject_server.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class JwtTokenUtil {
    @Value("${jwt.token.validity}")
    private int validitySeconds;
    private String secretKey;
    private final Map<String, LocalDateTime> activeTokens = new ConcurrentHashMap<>();//singleton and thread safe

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
        boolean isNew = (activeTokens.put(token,LocalDateTime.now())==null);
        log.info("Token added. Current activeTokens: {}",activeTokens);
        return isNew;
    }

    public void updateLastActivity(String token) {
        log.info("entering updateLastActivity token: {}", token);
        if (activeTokens.containsKey(token)) {
            activeTokens.put(token, LocalDateTime.now());
        } else {
            System.out.println("Token not found in active tokens.");
        }
        log.debug("Token updated. Current activeTokens: {}",activeTokens);

    }

    public void removeToken(String token) {
        log.info("entering removeToken token: {}", token);
        activeTokens.remove(token);
        log.info("Token removed. Current activeTokens: {}",activeTokens);
    }

    public boolean checkToken(String token) {
        log.info("entering checkToken token: {}", token);
        log.info("Current activeTokens: {}",activeTokens);
        LocalDateTime tokenDate  = activeTokens.get(token);
        if (activeTokens.get(token).plusSeconds(validitySeconds).isAfter(LocalDateTime.now())) {
            updateLastActivity(token);
            return true;
        }
        log.error("token is outdated");
        return false;
    }

}