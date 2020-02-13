package com.zpyu.springboot.controller;

import com.github.pagehelper.PageInfo;
import com.zpyu.springboot.entity.Account;
import com.zpyu.springboot.service.base.IAccountService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/oa/account")
public class AccountController {

	@Reference(version = "1.0.0")
	private IAccountService accountService;

	@ModelAttribute
	public Account get(@RequestParam(required = false, name = "id") String id) {
		Account account = new Account();
		if (!StringUtils.isEmpty(id) && !("null".equals(id))) {
			account = accountService.get(id);
		}
		return account;
	}

	@RequestMapping("/regist")
	public String regist() {
		return "account/registForm";
	}

	@RequestMapping("/regist/save")
	@ResponseBody
	public Object registSave(Account account) {
		ResponseStatus status = null;
		status = accountService.save(account);
		return status.getCode();
	}

	@RequestMapping("save")
	@ResponseBody
	public Object save(Account account) {
		System.out.println("------------------------保存用户信息");
		account.setRole("user");
		ResponseStatus status = null;
		if (null != account.getId()) {
			status = accountService.update(account);
		} else {
			status = accountService.save(account);
		}
		return status.getCode();
	}

	@RequestMapping("/login")
	public String login() {
		return "account/login";
	}

	@RequestMapping("/validataAccount")
	@ResponseBody
	public Object validateUser(Account account, HttpServletRequest request) {
		account = accountService.validateUser(account);
		if (null != account) {
			HttpSession session = request.getSession();
			account = accountService.getAccountInfo(account.getId());
			session.setAttribute("account", account);
			System.out.println("----------------------登录成功");
		}
		return account;
	}

	@RequestMapping("/logOut")
	public String logOut(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("account");
		System.out.println("----------------------退出登录");
		return "account/login";
	}

	@RequestMapping("/list")
	public String list(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(name = "pageSize", defaultValue = "2") int pageSize, Model model, Account account) {
		PageInfo<Account> page = accountService.findPage(pageNum, pageSize);
		model.addAttribute("account", account);
		model.addAttribute("page", page);
		return "account/list";
	}

	@RequestMapping("del")
	@ResponseBody
	public Object del(String id) {
		int count = accountService.delAccount(id);
		return count;
	}

	@RequestMapping("/profile")
	public Object profile(Account account, Model model) {
		model.addAttribute("account", account);
		return "account/registForm";
	}
}
