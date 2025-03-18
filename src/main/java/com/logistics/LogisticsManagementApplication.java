package com.logistics;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.logistics.repository")
public class LogisticsManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogisticsManagementApplication.class, args);
    }
}
