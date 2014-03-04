package org.jooq.demo.modules;

import org.h2.jdbcx.JdbcDataSource;
import org.jooq.CachedConfiguration;
import org.jooq.SQLDialect;
import org.jooq.cache.impl.DefaultCachedConfiguration;
import org.jooq.demo.db.DemoConnectionProvider;

import com.google.inject.AbstractModule;
import com.zaxxer.hikari.HikariConfig;

public class DaoModule extends AbstractModule {

	@Override
	protected void configure() {
		// pool configuration (should be loaded from a config file)
		HikariConfig config = new HikariConfig();
		config.setMaximumPoolSize(100);
		config.setDataSourceClassName(JdbcDataSource.class.getName());
		config.addDataSourceProperty("url", "jdbc:h2:mem:example");
		config.addDataSourceProperty("user", "sa");
		config.addDataSourceProperty("password", "");
		
		// dao configuration
		final CachedConfiguration configuration = new DefaultCachedConfiguration();
		configuration.set(new DemoConnectionProvider(config));
		configuration.set(SQLDialect.H2);
		configuration.settings().setRenderSchema(false);

		// mapping
		bind(CachedConfiguration.class).toInstance(configuration);
	}

}
