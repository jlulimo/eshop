package com.nnfs.api.account.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.nnfs.api.account.dao.AccountDao;
import com.nnfs.api.account.domain.Account;
import com.nnfs.api.account.mapper.AccountMapper;

public class AccountDaoImpl extends GenericDaoImpl<Account> implements AccountDao {
	
	@Autowired
	private AccountMapper accountMapper;
	
	public int count() {
		return accountMapper.count();
	}


}
