package couponsProject.couponsProject_server.exseptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

/**
 * Global exception handler for the application.
 * Provides centralized exception handling across all @RequestMapping methods.
 *
 * @ControllerAdvice Indicates that this class provides @ExceptionHandler methods for multiple controllers
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Handles NoSuchElementException.
     * @param e The exception
     * @return ResponseEntity with NOT_FOUND status and error message
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElement(NoSuchElementException e){
        log.info("NoSuchElementException status: {}",HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    /**
     * Handles CompanyException.
     * @param e The exception
     * @return ResponseEntity with FORBIDDEN status and error message
     */
    @ExceptionHandler(CompanyException.class)
    public ResponseEntity<String> companyException(CompanyException e){
        log.info("companyException status: {}",HttpStatus.FORBIDDEN);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }
    /**
     * Handles CustomerException.
     * @param e The exception
     * @return ResponseEntity with FORBIDDEN status and error message
     */
    @ExceptionHandler(CustomerException.class)
    public ResponseEntity<String> customerException(CustomerException e){
        log.info("CustomerException status: {}",HttpStatus.FORBIDDEN);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }

    /**
     * Handles CouponException.
     * @param e The exception
     * @return ResponseEntity with FORBIDDEN status and error message
     */

    @ExceptionHandler(CouponException.class)
    public ResponseEntity<String> couponException(CouponException e){
        log.info("couponException status: {}",HttpStatus.FORBIDDEN);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }
}
