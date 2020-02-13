package com.zpyu.springboot.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zpyu.springboot.dao.PermissionDao;
import com.zpyu.springboot.entity.Permission;
import com.zpyu.springboot.service.base.IPermissionService;
import com.zpyu.springboot.utils.ResponseStatus;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(version = "1.0.0",timeout = 10000,interfaceClass = IPermissionService.class )
@Component
@Transactional(readOnly = true)
public class PermissionServiceImpl implements IPermissionService {

	@Autowired
	private PermissionDao dao;

	/**
	 * 查询权限list
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Override
	public PageInfo<Permission> findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		List<Permission> list = dao.selectByExample(null);
		return new PageInfo<Permission>(list);
		
	}

	@Override
	@Transactional(readOnly = false)
	public Permission save(Permission entity) {
		return save(entity);
	}


	@Override
	@Transactional(readOnly = false)
	public ResponseStatus delete(Integer id) {
		try {
			if (null != id) {
				dao.deleteByPrimaryKey(id);
			} else {
				throw new RuntimeException();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return ResponseStatus.buildStatus();
	}

	@Override
	public Permission get(Integer id) {
		return dao.selectByPrimaryKey(id);
	}
}
