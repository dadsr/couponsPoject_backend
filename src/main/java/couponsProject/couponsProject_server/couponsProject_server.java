package couponsProject.couponsProject_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class couponsProject_server {
    public static void main(String[] args) {
        SpringApplication.run(couponsProject_server.class, args);

    }
}

