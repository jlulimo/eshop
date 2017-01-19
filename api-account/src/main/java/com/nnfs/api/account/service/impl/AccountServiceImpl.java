package com.nnfs.api.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnfs.api.account.dao.AccountDao;
import com.nnfs.api.account.domain.Account;
import com.nnfs.api.account.dto.AccountDto;
import com.nnfs.api.account.service.AccountService;

@Service
public class AccountServiceImpl extends GenericServiceImpl<AccountDto, Account> implements AccountService {

	@Autowired
	private AccountDao accountDaoImpl;

	@Override
	public int count() {
		return accountDaoImpl.count();
	}

	@Override
	public AccountDto getAccountByName(String accountName) {
		return this.convertToDto(this.accountDaoImpl.getAccountByName(accountName));
	}

	@Override
	public Account convertToDomain(AccountDto t) {
		Account domain = new Account();
		domain.setAccountId(t.getAccountId());
		domain.setDeleted(t.isDeleted());
		domain.setEnable(t.isEnable());
		domain.setGroups(t.getGroups());
		domain.setId(t.getId());
		domain.setLocked(t.isLocked());
		domain.setName(t.getName());
		domain.setPassword(t.getPassword());
		domain.setRoles(t.getRoles());
		domain.setSalt(t.getSalt());
		domain.setType(t.getType());
		return domain;
	}

	@Override
	public AccountDto convertToDto(Account d) {
		AccountDto dto = new AccountDto();
		dto.setAccountId(d.getAccountId());
		dto.setDeleted(d.isDeleted());
		dto.setEnable(d.isEnable());
		dto.setGroups(d.getGroups());
		dto.setId(d.getId());
		dto.setLocked(d.isLocked());
		dto.setName(d.getName());
		dto.setPassword(d.getPassword());
		dto.setRoles(d.getRoles());
		dto.setSalt(d.getSalt());
		dto.setType(d.getType());
		return dto;
	}

}
