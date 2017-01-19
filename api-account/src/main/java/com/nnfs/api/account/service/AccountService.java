package com.nnfs.api.account.service;

import com.nnfs.api.account.dto.AccountDto;

public interface AccountService extends GenericService<AccountDto> {
	public int count();

	public AccountDto getAccountByName(String accountName);
}
