package dao;

import javax.annotation.Resource;

import org.junit.Test;

import com.nnfs.api.account.dao.AccountDao;

import base.TestBase;


public class AccountDaoImplTest extends TestBase {
	@Resource(name="accountDaoImpl")
	private AccountDao dao;
	
	@Test
	public void testCount() {
		int count = dao.count();
		System.out.println(count);
	}

}
