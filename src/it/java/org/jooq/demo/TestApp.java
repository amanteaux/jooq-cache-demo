package org.jooq.demo;

import java.sql.Connection;

import org.h2.jdbcx.JdbcDataSource;
import org.jooq.CachedConfiguration;
import org.jooq.SQLDialect;
import org.jooq.cache.impl.DefaultCachedConfiguration;
import org.jooq.demo.db.DbInit;
import org.jooq.demo.db.DemoConnectionProvider;
import org.jooq.demo.modules.DaoModule;
import org.jooq.demo.modules.ServiceModule;
import org.junit.Before;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.zaxxer.hikari.HikariConfig;

public abstract class TestApp {
	
	private Injector injector;
	
	@Before
	public void initTestApp() {
		injector = Guice.createInjector(new DaoModule(cachedConfigurationInMemory()), new ServiceModule());
		try(Connection connection = injector.getInstance(CachedConfiguration.class).connectionProvider().acquire()){
			DbInit.init(connection);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	protected Injector injector() {
		return injector;
	}
	
	// internal
	
	// integration test should use an in-memory database
	private CachedConfiguration cachedConfigurationInMemory() {
		HikariConfig config = new HikariConfig();
		config.setMinimumPoolSize(10);
		config.setMaximumPoolSize(10);
		config.setDataSourceClassName(JdbcDataSource.class.getName());
		config.addDataSourceProperty("url", "jdbc:h2:mem:example");
		config.addDataSourceProperty("user", "sa");
		config.addDataSourceProperty("password", "");
		
		// dao configuration
		final CachedConfiguration configuration = new DefaultCachedConfiguration();
		configuration.set(new DemoConnectionProvider(config));
		configuration.set(SQLDialect.H2);
		configuration.settings().setRenderSchema(false);
		
		return configuration;
	}

}
