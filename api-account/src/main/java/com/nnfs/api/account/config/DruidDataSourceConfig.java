package com.nnfs.api.account.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
public class DruidDataSourceConfig implements EnvironmentAware {
	private static final Logger logger = LoggerFactory.getLogger(DruidDataSourceConfig.class);
	private RelaxedPropertyResolver propertyResolver;

	@Override
	public void setEnvironment(Environment environment) {
		this.propertyResolver = new RelaxedPropertyResolver(environment, "spring.datasource.");
	}

	@Bean(initMethod = "init", destroyMethod = "close")
	public DataSource dataSource() {
		DruidDataSource druidDataSource = new DruidDataSource();
		// mybatis 不支持setType = druid
		// druidDataSource.setDbType(this.propertyResolver.getProperty("type"));
		druidDataSource.setUrl(this.propertyResolver.getProperty("url"));
		druidDataSource.setDriverClassName(this.propertyResolver.getProperty("driver-class-name"));
		druidDataSource.setUsername(this.propertyResolver.getProperty("username"));
		druidDataSource.setPassword(this.propertyResolver.getProperty("password"));
		druidDataSource.setInitialSize(Integer.valueOf(this.propertyResolver.getProperty("initial-size")));
		druidDataSource.setMinIdle(Integer.valueOf(this.propertyResolver.getProperty("min-idle")));
		druidDataSource.setMaxWait(Long.valueOf(this.propertyResolver.getProperty("max-wait")));
		druidDataSource.setMaxActive(Integer.valueOf(this.propertyResolver.getProperty("max-active")));
		druidDataSource.setTimeBetweenEvictionRunsMillis(
				Long.valueOf(this.propertyResolver.getProperty("time-between-eviction-runs-millis")));
		druidDataSource.setMinEvictableIdleTimeMillis(
				Long.parseLong(this.propertyResolver.getProperty("min-evictable-idle-time-millis")));
		druidDataSource.setTestWhileIdle(Boolean.valueOf(this.propertyResolver.getProperty("test-while-idle")));
		druidDataSource.setTestOnBorrow(Boolean.valueOf(this.propertyResolver.getProperty("test-on-borrow")));
		druidDataSource.setTestOnReturn(Boolean.valueOf(this.propertyResolver.getProperty("test-on-return")));
		druidDataSource.setValidationQuery(this.propertyResolver.getProperty("validation-query"));
		druidDataSource.setPoolPreparedStatements(
				Boolean.valueOf(this.propertyResolver.getRequiredProperty("pool-prepared-statements")));
		druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(
				Integer.valueOf(this.propertyResolver.getProperty("max-pool-prepared-statement-per-connection-size")));
		try {
			druidDataSource.setFilters(this.propertyResolver.getProperty("filters"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		logger.info("init druid complete.");
		return druidDataSource;
	}

	/**
	 * MyBatis自动参与到spring事务管理中，无需额外配置，
	 * 只要org.mybatis.spring.SqlSessionFactoryBean引用的数据源与DataSourceTransactionManager引用的数据源一致即可，否则事务管理会不起作用
	 */
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		// mybatis分页
		// PageHelper pageHelper = new PageHelper();
		// Properties props = new Properties();
		// props.setProperty("dialect", "mysql");
		// props.setProperty("reasonable", "true");
		// props.setProperty("supportMethodsArguments", "true");
		// props.setProperty("returnPageInfo", "check");
		// props.setProperty("params", "count=countSql");
		// pageHelper.setProperties(props); //添加插件
		// sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper});
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:config/mybatis/mapper/*.xml"));
		sqlSessionFactoryBean.setTypeAliasesPackage("com.nnfs.api.account.domain");
		return sqlSessionFactoryBean.getObject();
	}

	@Bean
	public PlatformTransactionManager transactionManager() throws SQLException {
		return new DataSourceTransactionManager(dataSource());
	}
}
