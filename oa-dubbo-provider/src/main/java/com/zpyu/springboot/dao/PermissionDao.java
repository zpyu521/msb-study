package com.zpyu.springboot.dao;

import com.zpyu.springboot.dao.base.MyBatisBaseDao;
import com.zpyu.springboot.entity.Permission;

/**
 * PermissionDao继承基类
 */
public interface PermissionDao extends MyBatisBaseDao<Permission, Integer, PermissionExample> {
}