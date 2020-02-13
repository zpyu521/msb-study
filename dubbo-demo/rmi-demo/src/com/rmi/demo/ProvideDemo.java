package com.rmi.demo;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * @Author: zpyu521
 * @Date: 2019/8/9
 * @Description:
 * @Version: 1.0
 */
public class ProvideDemo {

    public static void main(String[] args) throws RemoteException {
        //初始化要发布的服务
        ISay iSay = new SayImpl();
        //开启本地服务
        ISay remote = (ISay) UnicastRemoteObject.exportObject(iSay, 999);
        //初始化注册中心
        Registry registry = LocateRegistry.createRegistry(666);
        //注册服务
        registry.rebind("sayService",remote);
    }
}
