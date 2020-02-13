package com.zpyu.springboot.service.base;

import com.github.pagehelper.PageInfo;
import com.zpyu.springboot.controller.ResponseStatus;
import com.zpyu.springboot.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * @Author: zpyu521
 * @Date: 2019/8/10
 * @Description:
 * @Version: 1.0
 */
public interface IRoleService {
    PageInfo<Role> findPage(int pageNum, int pageSize);

    PageInfo<Role> findRolePmsPage(int pageNum, int pageSize);

    Map<Object, Object> finAll(int limit, int offset, String searchName);

    void saveRolePermission(Role role);

    List<Role> findList();

    Role save(Role entity);

    ResponseStatus delete(Integer id);

    Role get(Integer id);
}
