package couponsProject.couponsProject_server.exseptions;

import couponsProject.couponsProject_server.security.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message) {
        super(message);
        log.error("throwing AuthenticationException message: {}", message);
    }
}
