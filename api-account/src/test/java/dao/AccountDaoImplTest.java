package dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnfs.api.account.App;
import com.nnfs.api.account.dao.AccountDao;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class AccountDaoImplTest {
	@Resource(name="accountDaoImpl")
	private AccountDao dao;
	
	@Test
	public void testCount() {
		int count = dao.count();
		System.out.println(count);
	}

}
