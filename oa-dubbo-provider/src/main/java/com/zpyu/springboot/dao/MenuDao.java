package com.zpyu.springboot.dao;

import com.zpyu.springboot.dao.base.MyBatisBaseDao;
import com.zpyu.springboot.entity.Menu;

/**
 * MenuDAO继承基类
 */
public interface MenuDao extends MyBatisBaseDao<Menu, Integer, MenuExample> {
}