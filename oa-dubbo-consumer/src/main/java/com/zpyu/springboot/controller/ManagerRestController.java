package com.zpyu.springboot.controller;

import com.zpyu.springboot.entity.Permission;
import com.zpyu.springboot.entity.Role;
import com.zpyu.springboot.service.base.IPermissionService;
import com.zpyu.springboot.service.base.IRoleService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * restful风格URI的controller
 * 只和用户交换JSON数据
 * @author Administrator
 *
 */

@RestController
@RequestMapping("/oa/api/v1/manager/permission")
public class ManagerRestController {
	@Reference(version = "1.0.0")
	private IRoleService roleService;

	@Reference(version = "1.0.0")
	private IPermissionService permissionService;
	
	
	@RequestMapping("/saveRole")
	public ResponseStatus saveRole(Role role) {
		try {
			roleService.save(role);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return ResponseStatus.buildStatus();
	}
	
	@RequestMapping("/savePermission")
	public ResponseStatus savePermission(Permission permission) {
		try {
			permissionService.save(permission);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return ResponseStatus.buildStatus();
	}
	
	@RequestMapping("/saveRolePermission")
	public ResponseStatus saveRolePermission(@RequestBody Role role) {
		try {
			roleService.saveRolePermission(role);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return ResponseStatus.buildStatus();
	}
	
	@RequestMapping("/deleteRole")
	public ResponseStatus deleteRole(Integer id) {
		return roleService.delete(id);
	}
	
	@RequestMapping("/deletePermission")
	public ResponseStatus deletePermission(Integer id) {
		return permissionService.delete(id);
	}
	
}
