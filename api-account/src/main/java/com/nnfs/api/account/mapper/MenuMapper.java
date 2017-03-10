package com.nnfs.api.account.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nnfs.api.account.domain.Menu;

@Mapper
public interface MenuMapper extends GenericMapper<Menu>{
	Menu getMenuById(String id);
	List<Menu> getChildrenByParentId(String pid);
}
