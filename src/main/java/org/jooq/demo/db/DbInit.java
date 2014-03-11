package org.jooq.demo.db;

import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;

import org.h2.tools.RunScript;

public class DbInit {
	
	public static final void init(Connection connection) throws SQLException {
		RunScript.execute(connection, new InputStreamReader(DbInit.class.getResourceAsStream("/database_init.sql")));
	}

}
