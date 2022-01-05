package com.example.thejavatest;

import lombok.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class TheJavaTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TheJavaTestApplication.class, args);
    }
}
