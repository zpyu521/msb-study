package com.zpyu.springboot.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zpyu.springboot.dao.RoleDao;
import com.zpyu.springboot.dao.RoleExample;
import com.zpyu.springboot.entity.Role;
import com.zpyu.springboot.service.base.IRoleService;
import com.zpyu.springboot.utils.ResponseStatus;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(version = "1.0.0",timeout = 10000,interfaceClass = IRoleService.class )
@Component
@Transactional(readOnly = true)
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private RoleDao dao;

	@Override
	public PageInfo<Role> findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);

		RoleExample example = new RoleExample();
		List<Role> list = dao.selectByExample(example);
		// 第二个参数是页码数默认值就是8
		return new PageInfo<Role>(list);

	}

	/**
	 * 查询角色权限list
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Override
	public PageInfo<Role> findRolePmsPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);

		List<Role> list = dao.selectRolePmsList();
		return new PageInfo<Role>(list);

	}

	@Override
	public Map<Object, Object> finAll(int limit, int offset, String searchName) {
		RoleExample example = new RoleExample();
		example.setLimit(limit);
		example.setOffset(Long.valueOf(offset));
		if (!StringUtils.isEmpty(searchName)) {
			example.createCriteria().andNameLike(searchName);
		}
		List<Role> list = dao.selectByExample(example);
		long total = dao.countByExample(new RoleExample());

		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("total", total);
		map.put("rows", list);
		return map;
	}

	@Override
	@Transactional(readOnly = false)
	public void saveRolePermission(Role role) {
		dao.saveRolePermission(role);
	}

	@Override
	public List<Role> findList() {
		List<Role> list = dao.selectRolePmsList();
		return list;
	}
	@Override
	@Transactional(readOnly = false)
	public Role save(Role entity) {
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
	public Role get(Integer id) {
		return dao.selectByPrimaryKey(id);
	}
}
