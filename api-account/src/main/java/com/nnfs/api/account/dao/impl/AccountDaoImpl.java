package com.nnfs.api.account.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nnfs.api.account.dao.AccountDao;
import com.nnfs.api.account.domain.Account;
import com.nnfs.api.account.mapper.AccountMapper;

@Repository
public class AccountDaoImpl extends GenericDaoImpl<Account> implements AccountDao {

	@Autowired
	private AccountMapper accountMapper;
	
	@Autowired
	public void setBaseMapper(){
		super.setMapper(this.accountMapper);
	}

	public void setAccountMapper(AccountMapper accountMapper) {
		this.accountMapper = accountMapper;
	}

	public int count() {
		return accountMapper.count();
	}

	@Override
	public Account getAccountByName(String accountName) {
		return accountMapper.getAccountByName(accountName);
	}

	@Override
	public Account getByAccountId(String accountId) {
		return accountMapper.getByAccountId(accountId);
	}

}
