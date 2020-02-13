package com.zpyu.springboot.service;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * @Author: zpyu521
 * @Date: 2019/8/9
 * @Description:
 * @Version: 1.0
 */
@Service(version = "1.0.0",timeout = 10000,interfaceClass = ISay.class)
@Component
public class SayImpl implements ISay {

    @Override
    public String say(String name){
        System.out.println("hi:"+name);
        return "hi:"+name;
    }
}
