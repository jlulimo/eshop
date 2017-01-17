package com.nnfs.api.account.dao;

import com.nnfs.api.account.domain.Account;

public interface AccountDao extends GenericDao<Account> {
	public int count();
}
