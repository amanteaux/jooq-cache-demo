package org.jooq.demo.db.daos;

import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;

import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.RunScript;
import org.jooq.CachedConfiguration;
import org.jooq.SQLDialect;
import org.jooq.cache.impl.DefaultCachedConfiguration;
import org.jooq.demo.App;
import org.jooq.demo.db.DemoConnectionProvider;

import com.zaxxer.hikari.HikariConfig;

class TestUtils {

	static CachedConfiguration cachedConfigurationInMemory() {
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
	
	static void initDb(CachedConfiguration configuration) throws SQLException {
		Connection connection = configuration.connectionProvider().acquire();
		RunScript.execute(connection, new InputStreamReader(App.class.getResourceAsStream("/database_init.sql")));
		connection.close();
	}
	
}
