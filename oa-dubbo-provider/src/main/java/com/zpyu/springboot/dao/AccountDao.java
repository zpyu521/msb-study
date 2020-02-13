package com.zpyu.springboot.dao;

import com.zpyu.springboot.dao.base.MyBatisBaseDao;
import com.zpyu.springboot.entity.Account;

import java.util.List;

//@Mapper
public interface AccountDao extends MyBatisBaseDao<Account, Integer, AccountExample>{

	List<Account> selectAccountRole();

	Account selectAccountInfo(Integer id);

}
