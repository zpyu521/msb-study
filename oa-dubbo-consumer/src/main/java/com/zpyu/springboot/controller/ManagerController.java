package com.zpyu.springboot.controller;

import com.github.pagehelper.PageInfo;
import com.zpyu.springboot.controller.base.BaseController;
import com.zpyu.springboot.entity.Account;
import com.zpyu.springboot.entity.Permission;
import com.zpyu.springboot.entity.Role;
import com.zpyu.springboot.service.base.IAccountService;
import com.zpyu.springboot.service.base.IPermissionService;
import com.zpyu.springboot.service.base.IRoleService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 用户账户相关
 * 
 * @author Administrator
 *
 */

@RequestMapping("/oa/manager")
@Controller
public class ManagerController extends BaseController {
	@Reference(version = "1.0.0")
	private IAccountService accountService;
	@Reference(version = "1.0.0")
	private IRoleService roleService;
	@Reference(version = "1.0.0")
	private IPermissionService permissionService;

	// account ----------------------- ↓
	@RequestMapping("/accountList")
	public String list(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(name = "pageSize", defaultValue = "2") int pageSize, Model model, Account account) {
		PageInfo<Account> page = accountService.findPage(pageNum, pageSize);
		model.addAttribute("account", account);
		model.addAttribute("page", page);
		return "account/list";
	}

	@RequestMapping("/accountRoleList")
	public Object accountRoleList(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(name = "pageSize", defaultValue = "4") int pageSize, Model model, Account account) {
		PageInfo<Account> page = accountService.findAccountRolePage(pageNum, pageSize);
		model.addAttribute("page", page);
		return "manager/accountRoleList";
	}

	// account ----------------------- ↑

	// role ----------------------- ↓
	@RequestMapping("/roleList")
	public String roleList(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(name = "pageSize", defaultValue = "2") int pageSize, Model model, Role role) {
		PageInfo<Role> page = roleService.findPage(pageNum, pageSize);
		model.addAttribute("page", page);
		return "manager/roleList";
	}

	@RequestMapping("/roleForm")
	public String roleForm(Model model, Integer id) {
		if (null != id) {
			Role role = roleService.get(id);
			model.addAttribute("entity", role);
		}
		return "manager/roleForm";
	}

	// bootstrap-table
	// search: 8
	// order: asc
	// offset: 0
	// limit: 10
	@RequestMapping("/roleJson")
	@ResponseBody

	public Object roleJson(@RequestParam(name = "search", required = false) String searchName,
			@RequestParam(name = "limit", defaultValue = "2") int limit,
			@RequestParam(name = "offset", defaultValue = "0") int offset) {
		Map<Object, Object> list = roleService.finAll(limit, offset, searchName);
		return list;
	}
	// role ----------------------- ↑

	// permission----------------------- ↓
	@RequestMapping("/rolePermissionList")
	public String rolePermissionList( Model model) {
		List<Role> list = roleService.findList();
		model.addAttribute("list", list);
		return "manager/rolePermissionList";
	}

	@RequestMapping("/configPms")
	public String configPms(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(name = "pageSize", defaultValue = "2") int pageSize, Model model, String roleId) {
		PageInfo<Permission> page = permissionService.findPage(pageNum, pageSize);
		
		model.addAttribute("roleId", roleId);
		model.addAttribute("page", page);
		return "manager/configPmsList";
	}

	@RequestMapping("/permissionList")
	public String permissionList(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(name = "pageSize", defaultValue = "2") int pageSize, Model model) {
		PageInfo<Permission> page = permissionService.findPage(pageNum, pageSize);
		model.addAttribute("page", page);
		return "manager/permissionList";
	}

	@RequestMapping("/permissionForm")
	public String permissionForm(Model model, Integer id) {
		if (null != id) {
			Permission permission = permissionService.get(id);
			model.addAttribute("entity", permission);
		}
		return "manager/permissionForm";
	}

	// permission----------------------- ↑

}
