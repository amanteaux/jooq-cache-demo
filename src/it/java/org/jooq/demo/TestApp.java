package org.jooq.demo;

import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;

import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.RunScript;
import org.jooq.CachedConfiguration;
import org.jooq.SQLDialect;
import org.jooq.cache.impl.DefaultCachedConfiguration;
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
	public void initTestApp() throws SQLException {
		injector = Guice.createInjector(new DaoModule(cachedConfigurationInMemory()), new ServiceModule());
		initDb(injector.getInstance(CachedConfiguration.class));
	}
	
	protected Injector injector() {
		return injector;
	}
	
	// internal
	
	private void initDb(CachedConfiguration configuration) throws SQLException {
		Connection connection = configuration.connectionProvider().acquire();
		RunScript.execute(connection, new InputStreamReader(App.class.getResourceAsStream("/database_init.sql")));
		connection.close();
	}
	
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
