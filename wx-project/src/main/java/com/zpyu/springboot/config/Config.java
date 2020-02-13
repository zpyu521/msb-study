package com.zpyu.springboot.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Config {
    @Value("${wx.appsecret}")
    private String appsecret;
    
    @Value("${wx.appID}")
    private String appID;

    @Value("${wx.token}")
    private String token;
}
