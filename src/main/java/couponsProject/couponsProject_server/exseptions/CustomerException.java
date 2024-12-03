package couponsProject.couponsProject_server.exseptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomerException extends RuntimeException {
    public CustomerException(String message) {
        super(message);
        log.info("throwing CustomerException message: {}", message);
    }
}
