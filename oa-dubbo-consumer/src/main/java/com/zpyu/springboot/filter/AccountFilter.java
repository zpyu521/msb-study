package com.zpyu.springboot.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.zpyu.springboot.entity.Account;
import com.zpyu.springboot.entity.Permission;
import com.zpyu.springboot.entity.Role;

@Component
@WebFilter(urlPatterns = "/*")
public class AccountFilter implements Filter {
	// 不需要登录的 URI
	private final String[] IGNORE_URI = { "/oa/account/regist", "/oa/index", "/oa/account/validataAccount", "/css/",
			"/js/", "/oa/account/login", "/images/", "/oa/account/logOut", "/oa/error.html",
			"/oa/attachment/getBytes" };

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String url = req.getRequestURI();
		System.out.println("------------------" + url);
		boolean pass = canPassIgnore(url);
		if (pass) {
			chain.doFilter(req, res);
			return;
		}

		Account account = (Account) req.getSession().getAttribute("account");
		System.out.println("getSession account:" + account);
		if (null == account) {
			// 没登录 跳转登录页面
			res.sendRedirect("/oa/account/login");
			return;
		}
		List<Role> roleList = account.getRoleList();
		if (CollectionUtils.isEmpty(roleList) || !(roleList.get(0).getId() == 2)) {
			List<Permission> permissionList = account.getPermissionList();
			if (this.canVisit(permissionList, url) < 1) {
//				res.sendRedirect("/oa/error.html");
				//不改变location
				req.getRequestDispatcher("/oa/error.html").forward(req, res);
				return;
			}
		}

		System.out.println("----filter----" + url);
		chain.doFilter(req, res);
	}

	private long canVisit(List<Permission> permissionList, String url) {
		return permissionList.stream().filter(p -> p.getUri().equals(url)).count();
	}

	private boolean canPassIgnore(String uri) {

		// /index = uri

		// 判断 访问的URI 起始部分 是否包含 Ignore
		// 下级目录资源也能访问

		for (String val : IGNORE_URI) {

			if (uri.startsWith(val)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// 加载 Filter 启动之前需要的资源
		System.out.println("---------------AccountFilter Init....");
		Filter.super.init(filterConfig);
	}

}
