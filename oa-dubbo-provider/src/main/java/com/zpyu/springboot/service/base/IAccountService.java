package com.zpyu.springboot.service.base;

import com.github.pagehelper.PageInfo;
import com.zpyu.springboot.entity.Account;
import com.zpyu.springboot.utils.ResponseStatus;

/**
 * @Author: zpyu521
 * @Date: 2019/8/10
 * @Description:
 * @Version: 1.0
 */
public interface IAccountService {
    PageInfo<Account> findPage(int pageNum, int pageSize);

    Account get(String id);

    ResponseStatus save(Account account);

    Account validateUser(Account account);

    int delAccount(String id);

    ResponseStatus update(Account account);

    Account getAccountByloginName(String loginName);

    PageInfo<Account> findAccountRolePage(int pageNum, int pageSize);

    Account getAccountInfo(Integer id);
}
