package com.rmi.demo;

/**
 * @Author: zpyu521
 * @Date: 2019/8/9
 * @Description:
 * @Version: 1.0
 */
public class SayImpl implements ISay {

    @Override
    public String say(String name){
        System.out.println("hi:"+name);
        return "hi:"+name;
    }
}
