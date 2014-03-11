package org.jooq.demo;

import java.sql.Connection;
import java.sql.SQLException;

import org.jooq.CachedConfiguration;
import org.jooq.demo.db.DbInit;
import org.jooq.demo.modules.DaoModule;
import org.jooq.demo.modules.ServiceModule;

import com.google.inject.Guice;
import com.google.inject.Injector;


public class App {
	
	private final Injector injector = Guice.createInjector(new DaoModule(), new ServiceModule());
	
	public App() {
		// application init
		CachedConfiguration conf = injector.getInstance(CachedConfiguration.class);
		try(Connection connection = conf.connectionProvider().acquire()) {
			DbInit.init(connection);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public final Injector injector() {
		return injector;
	}
    
}
