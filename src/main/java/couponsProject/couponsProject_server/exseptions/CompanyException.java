package couponsProject.couponsProject_server.exseptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CompanyException extends RuntimeException {
    public CompanyException(String message) {
        super(message);
        log.error("throwing CompanyException message: {}", message);
    }
}
