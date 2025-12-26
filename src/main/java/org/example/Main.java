package org.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//  is Spring Boot
@SpringBootApplication
//  where is mapper interface
@MapperScan("org.example.mapper")
public class Main {
    public static void main(String[] args) {
        // launch
        SpringApplication.run(Main.class, args);
    }
}