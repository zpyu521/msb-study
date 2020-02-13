package com.rmi.demo;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @Author: zpyu521
 * @Date: 2019/8/9
 * @Description:
 * @Version: 1.0
 */
public class RunDemo {

    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry(666);
        ISay sayService = (ISay) registry.lookup("sayService");
        String say = sayService.say("444");
        System.out.println(say);
    }
}
