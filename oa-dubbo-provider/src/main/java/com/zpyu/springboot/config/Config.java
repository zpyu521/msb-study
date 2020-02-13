package com.zpyu.springboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Config {
    @Value(value = "${system.name}")
    private String systemName;
    @Value(value = "${file.prefix}")
    private String filePrefix;


    public String getSystemName() {
        return systemName;
    }

    public String getFilePrefix() {
        return filePrefix;
    }

}
