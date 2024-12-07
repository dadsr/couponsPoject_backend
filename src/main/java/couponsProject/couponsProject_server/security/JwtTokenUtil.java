package couponsProject.couponsProject_server.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.HashSet;


@Component
public class JwtTokenUtil {
    @Value("${jwt.token.validity}")
    private int validity;
    private String secretKey;
    private HashSet<String> activeTokens = new HashSet<>();

    public JwtTokenUtil() {
        if (this.secretKey == null || this.secretKey.isEmpty()) {
            this.secretKey = generateSecretKey();
        }
    }

    public String getSecretKey() {
        return secretKey;
    }


    private static String generateSecretKey() {
        // Generate a strong random key
        SecureRandom secureRandom = new SecureRandom();
        byte[] keyBytes = new byte[32]; // 256-bit key
        secureRandom.nextBytes(keyBytes);
        // Encode the key in Base64 for use as a JWT secret
         return Base64.getEncoder().encodeToString(keyBytes);
    }

    public String createToken(int id, String name, String email,String role) {
        Date now = new Date();
        Date expires = new Date(now.getTime() + validity);
        return "Bearer " + JWT.create()
                .withIssuer("com.garbage.collectors")
                .withIssuedAt(now)
                .withExpiresAt(expires)
                .withClaim("id",id)
                .withClaim("name", name)
                .withClaim("email", email)
                .withClaim("role", role)
                .sign(Algorithm.HMAC256(secretKey)); // Use HMAC256 for signing with a secret key
    }

    public boolean addToken(String token) {
        if(activeTokens.add(token))
            return true;
        return false;
    }

    public void removeToken(String token) {
        activeTokens.remove(token);
    }

    public boolean checkToken(String token) {
        if(activeTokens.contains(token))
            return true;
        return false;
    }

}