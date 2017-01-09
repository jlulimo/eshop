package com.nnfs.api.account.config;

import static org.mockito.Matchers.longThat;

import javax.activation.DataSource;

import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import com.alibaba.druid.pool.DruidDataSource;

public class DruidDataSourceConfig implements EnvironmentAware {

	private RelaxedPropertyResolver propertyResolver;
	@Override
	public void setEnvironment(Environment environment) {
		this.propertyResolver = new RelaxedPropertyResolver(environment,"spring.datasource.");
	}
	
	@Bean
	public DataSource dataSource(){
		System.out.println("druid 注入...");
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(this.propertyResolver.getProperty("url"));
		dataSource.setDriverClassName(this.propertyResolver.getProperty("driver-class-name"));
		dataSource.setUsername(this.propertyResolver.getProperty("username"));
		dataSource.setPassword(this.propertyResolver.getProperty("password"));
		dataSource.setInitialSize(Integer.valueOf( this.propertyResolver.getProperty("initial-size")));
		dataSource.setMinIdle(Integer.valueOf( this.propertyResolver.getProperty("min-idle")));
		dataSource.setMaxWait(Long.valueOf(this.propertyResolver.getProperty("max-wait")));
		dataSource.setMaxActive(Integer.valueOf(this.propertyResolver.getProperty("max-active")));
		return null;
	}

}
