package com.zpyu.springboot.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan("com.zpyu.springboot")
@ServletComponentScan("com.zpyu.springboot.listener")
public class WxProjectStater {

    public static void main(String[] args) {
        SpringApplication.run(WxProjectStater.class, args);
    }

}
