package com.nnfs.api.jms.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnfs.api.jms.App;
import com.nnfs.api.jms.domain.City;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class DaoTest {
	@Autowired
	private CityDao dao;
	@Test
	public void test() {
		City c =dao.selectCityById(1);
		System.out.print(c.getName());
	}
}
