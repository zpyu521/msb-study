package com.zpyu.springboot.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan("com.zpyu.springboot")
public class OaApplicationConsumer {

    public static void main(String[] args) {
        SpringApplication.run(OaApplicationConsumer.class, args);
    }

}
