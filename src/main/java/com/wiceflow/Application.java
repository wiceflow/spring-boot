package com.wiceflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author BF
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        System.out.println("Application");
        SpringApplication.run(Application.class, args);
    }
}
