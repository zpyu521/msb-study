package com.zpyu.springboot.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zpyu.springboot.dao.AccountDao;
import com.zpyu.springboot.dao.AccountExample;
import com.zpyu.springboot.entity.Account;
import com.zpyu.springboot.service.base.IAccountService;
import com.zpyu.springboot.utils.ResponseStatus;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(version = "1.0.0",timeout = 10000,interfaceClass = IAccountService.class )
@Component
@Transactional(readOnly = true)
public class AccountServiceImpl implements IAccountService {

	@Autowired
	private AccountDao accountDao;

	@Override
	public PageInfo<Account> findPage(int pageNum, int pageSize) {

		PageHelper.startPage(pageNum, pageSize);

		AccountExample example = new AccountExample();
		List<Account> list = accountDao.selectByExample(example);
		// 第二个参数是页码数
		return new PageInfo<Account>(list, 8);

	}

	/**
	 * 根据id获得实体
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Account get(String id) {
		return accountDao.selectByPrimaryKey(Integer.valueOf(id));
	}

	@Override
	@Transactional(readOnly = false)
	public ResponseStatus save(Account account) {
		try {
			accountDao.insert(account);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return ResponseStatus.buildStatus();
	}

	@Override
	public Account validateUser(Account account) {
		AccountExample example = new AccountExample();
		example.createCriteria().andLoginNameEqualTo(account.getLoginName()).andPasswordEqualTo(account.getPassword());
		List<Account> list = accountDao.selectByExample(example);
		if (list.size() == 1) {
			return list.get(0);
		}
//		多条重复数据也是错误
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public int delAccount(String id) {
		return accountDao.deleteByPrimaryKey(Integer.valueOf(id));
	}
	
	@Override
	@Transactional(readOnly = false)
	public ResponseStatus update(Account account) {
		try {
			accountDao.updateByPrimaryKey(account);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return ResponseStatus.buildStatus();
	}

	@Override
	public Account getAccountByloginName(String loginName) {
		AccountExample example = new AccountExample();
		example.createCriteria().andLoginNameEqualTo(loginName);
		List<Account> list = accountDao.selectByExample(example);
		if (list.size() == 1) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public PageInfo<Account> findAccountRolePage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Account> list = accountDao.selectAccountRole();
		// 第二个参数是页码数
		return new PageInfo<Account>(list, 8);
	}

	@Override
	public Account getAccountInfo(Integer id) {
		return accountDao.selectAccountInfo(id);
	}

}
