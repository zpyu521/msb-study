package com.zpyu.springboot.service.base;

import com.github.pagehelper.PageInfo;
import com.zpyu.springboot.controller.ResponseStatus;
import com.zpyu.springboot.entity.Permission;

/**
 * @Author: zpyu521
 * @Date: 2019/8/10
 * @Description:
 * @Version: 1.0
 */
public interface IPermissionService {
    PageInfo<Permission> findPage(int pageNum, int pageSize);

    Permission save(Permission entity);

    ResponseStatus delete(Integer id);

    Permission get(Integer id);
}
