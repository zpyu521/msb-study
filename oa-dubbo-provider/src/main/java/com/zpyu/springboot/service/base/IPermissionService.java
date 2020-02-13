package com.zpyu.springboot.service.base;

import com.github.pagehelper.PageInfo;
import com.zpyu.springboot.entity.Permission;
import com.zpyu.springboot.utils.ResponseStatus;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: zpyu521
 * @Date: 2019/8/10
 * @Description:
 * @Version: 1.0
 */
public interface IPermissionService {
    PageInfo<Permission> findPage(int pageNum, int pageSize);

    @Transactional(readOnly = false)
    Permission save(Permission entity);

    @Transactional(readOnly = false)
    ResponseStatus delete(Integer id);

    Permission get(Integer id);
}
