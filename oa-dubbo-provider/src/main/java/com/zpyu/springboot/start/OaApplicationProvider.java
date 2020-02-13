package com.zpyu.springboot.start;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.zpyu.springboot.dao")
@ComponentScan("com.zpyu.springboot")
public class OaApplicationProvider {

    public static void main(String[] args) {
        SpringApplication.run(OaApplicationProvider.class, args);
    }

}
