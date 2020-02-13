package com.zpyu.springboot.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weixin.popular.bean.user.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: zpyu521
 * @Date: 2019/8/16
 * @Description:
 * @Version: 1.0
 */
@WebFilter(filterName = "webFilter",urlPatterns ={"/profile*"} )
public class WxAuthFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(WxAuthFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("----------------初始化filter-----------------------");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        //这个user是框架封装的
        User user = (User) request.getSession().getAttribute("user");

        //user为null去登陆
        if (null == user) {
            String uri = request.getRequestURI();
            //response.sendRedirect(uri+);
        }



        chain.doFilter(request,response);
        return;
    }
}
