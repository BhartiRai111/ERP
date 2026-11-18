package com.paperbiz.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.paperbiz", "com.dataman"})
public class PaperbizApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaperbizApplication.class, args);
    }
}
