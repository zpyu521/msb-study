package com.zpyu.springboot.dao;

import com.zpyu.springboot.dao.base.MyBatisBaseDao;
import com.zpyu.springboot.entity.Role;

import java.util.List;

/**
 * RoleDao继承基类
 */
public interface RoleDao extends MyBatisBaseDao<Role, Integer, RoleExample> {

	List<Role> selectRolePmsList();

	void saveRolePermission(Role role);
}