package org.jooq.demo.modules;

import org.h2.jdbcx.JdbcDataSource;
import org.jooq.CachedConfiguration;
import org.jooq.SQLDialect;
import org.jooq.cache.impl.DefaultCachedConfiguration;
import org.jooq.demo.db.DemoConnectionProvider;

import com.google.inject.AbstractModule;
import com.zaxxer.hikari.HikariConfig;

public class DaoModule extends AbstractModule {

	final CachedConfiguration configuration;
	
	public DaoModule(CachedConfiguration configuration) {
		this.configuration = configuration;
	}
	
	public DaoModule() {
		// pool configuration
		HikariConfig config = new HikariConfig(getClass().getResource("/hikari.properties").getFile());
		config.setDataSourceClassName(JdbcDataSource.class.getName());
		
		// dao configuration
		configuration = new DefaultCachedConfiguration();
		configuration.set(new DemoConnectionProvider(config));
		configuration.set(SQLDialect.H2);
		configuration.settings().setRenderSchema(false);
	}

	@Override
	protected void configure() {
		bind(CachedConfiguration.class).toInstance(configuration);
	}

}
