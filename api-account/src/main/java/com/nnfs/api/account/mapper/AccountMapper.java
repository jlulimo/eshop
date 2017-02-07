package com.nnfs.api.account.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.nnfs.api.account.domain.Account;

@Mapper
public interface AccountMapper extends GenericMapper<Account> {
	public int count();

	public Account getAccountByName(String accountName);

	public Account getByAccountId(String accountId);
}
