package service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.nnfs.api.account.service.AccountService;

import base.TestBase;

public class AccountServiceImplTest  extends TestBase{

	@Autowired
	private AccountService accountServiceImpl;
	@Test
	public void test() {
		int count = this.accountServiceImpl.count();
		System.out.println(String.format("Account counts: [%d]", count));
	}

}
