package com.zpyu.springboot.listener;

import com.zpyu.springboot.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import weixin.popular.support.TicketManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class TicketManagerListener implements ServletContextListener {
	private static final Logger logger = LoggerFactory.getLogger(TicketManagerListener.class);
	@Autowired
	private Config config;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//WEB容器 初始化时调用
		//TicketManager 依赖 TokenManager，确保TokenManager.init 先被调用
		//TicketManager.init(config.getAppID());

		logger.info("***********************初始化TicketManager***********************");
		//2.6.1 版本新增，延迟5秒执行。
		TicketManager.init(config.getAppID(),5,60*119);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		//WEB容器  关闭时调用
		logger.info("***********************关闭TicketManager***********************");
		TicketManager.destroyed();
	}
}
