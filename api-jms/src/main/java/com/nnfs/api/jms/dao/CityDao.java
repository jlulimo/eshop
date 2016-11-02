package com.nnfs.api.jms.dao;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nnfs.api.jms.domain.City;

@Component
public class CityDao {

	@Autowired
	private SqlSession sqlSession;

	public City selectCityById(long id) {
		return this.sqlSession.selectOne("selectCityById", id);
	}

}
