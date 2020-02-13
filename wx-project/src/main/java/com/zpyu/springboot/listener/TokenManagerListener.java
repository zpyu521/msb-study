package com.zpyu.springboot.listener;

import com.zpyu.springboot.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import weixin.popular.support.TokenManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class TokenManagerListener implements ServletContextListener {
    private static Logger logger = LoggerFactory.getLogger(TokenManagerListener.class);
    @Autowired
    private Config config;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("***********************初始化TokenManager***********************");
        //WEB容器 初始化时调用
        TokenManager.init(config.getAppID(), config.getAppsecret());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("***********************关闭TokenManager***********************");
        //WEB容器  关闭时调用
        TokenManager.destroyed();
    }
}
