package com.camelcase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Ravi Panchal
 */
@SpringBootApplication
@EnableScheduling
public class ProducerMain {
    public static void main(String[] args) {
        SpringApplication.run(ProducerMain.class);
    }
}