package couponsProject.couponsProject_server.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Order(1)
public class CorsFilter  extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "OPTIONS, GET, DELETE, POST, PUT, HEAD");
        response.setHeader("Access-Control-Allow-Headers", "authorization, Origin, Accept, content-type, Access-Control-Request-Method, Access-Control-Request-Headers");
        if ("OPTIONS".equalsIgnoreCase(request.getMethod()))
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
        else
            filterChain.doFilter(request, response);

    }
}

