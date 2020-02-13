package com.rmi.demo;

import java.rmi.Remote;

/**
 * @Author: zpyu521
 * @Date: 2019/8/9
 * @Description:
 * @Version: 1.0
 */
public interface ISay extends Remote {
    String say(String name) throws Exception;
}
