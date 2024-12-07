package couponsProject.couponsProject_server.exseptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message) {
        super(message);
        log.info("throwing AuthenticationException message: {}", message);
    }
}
