package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.dao") //要用dao时到相应的包下找
public class SpringBootStart {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootStart.class);
    }
}
