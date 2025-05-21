package com.vip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableCaching
@SpringBootApplication//(scanBasePackages = {"com.clara"})
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
