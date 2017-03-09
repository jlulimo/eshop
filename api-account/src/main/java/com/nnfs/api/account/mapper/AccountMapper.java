package com.nnfs.api.account.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.nnfs.api.account.domain.Account;

@Mapper
public interface AccountMapper extends GenericMapper<Account> {
	int count();

	Account getAccountByName(String accountName);

	Account getByAccountId(String accountId);
}
