package com.nnfs.api.account.dao;

import com.nnfs.api.account.domain.Account;

public interface AccountDao {
	Account getAccountById(String accountId);
	Account getAccountByName(String name);
	 
}
