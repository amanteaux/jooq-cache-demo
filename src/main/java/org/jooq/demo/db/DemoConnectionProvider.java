package org.jooq.demo.db;

import java.sql.Connection;
import java.sql.SQLException;

import org.jooq.ConnectionProvider;
import org.jooq.exception.DataAccessException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DemoConnectionProvider implements ConnectionProvider  {
	
	private final HikariDataSource dataSource;
	
	public DemoConnectionProvider(HikariConfig config) {
		this.dataSource = new HikariDataSource(config);
	}

	@Override
	public Connection acquire() throws DataAccessException {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new DataAccessException("", e);
		}
	}

	@Override
	public void release(Connection connection) throws DataAccessException {
		try {
			connection.close();
		} catch (SQLException e) {
			throw new DataAccessException("", e);
		}
	}

}
