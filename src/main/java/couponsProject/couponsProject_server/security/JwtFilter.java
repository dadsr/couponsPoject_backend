package couponsProject.couponsProject_server.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import couponsProject.couponsProject_server.exseptions.AuthenticationException;
import couponsProject.couponsProject_server.services.ClientTypeEnum;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
@Order(2)
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7); // Remove "Bearer " prefix

            try {
                if(!jwtTokenUtil.checkToken(token)) {
                    throw new AuthenticationException("Invalid token");
                }
                DecodedJWT decodedJWT = validateToken(token);

                // Extract claims from the token
                int id = decodedJWT.getClaim("id").asInt();
                String name = decodedJWT.getClaim("name").asString();
                String email = decodedJWT.getClaim("email").asString();
                ClientTypeEnum role = ClientTypeEnum.valueOf(decodedJWT.getClaim("role").asString());

            } catch (JWTVerificationException| AuthenticationException e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);//401
                response.getWriter().write("Invalid or expired token");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().equals("/login");
    }

    private DecodedJWT validateToken(String token) throws JWTVerificationException {

        Algorithm algorithm = Algorithm.HMAC256(jwtTokenUtil.getSecretKey());
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("com.garbage.collectors") // Match issuer
                .build();
        return verifier.verify(token); // Verify and decode the token
    }}


