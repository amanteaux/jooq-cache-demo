package org.jooq.demo;

import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.h2.tools.RunScript;
import org.jooq.CachedConfiguration;
import org.jooq.demo.db.beans.User;
import org.jooq.demo.exception.AuthentificationException;
import org.jooq.demo.modules.DaoModule;
import org.jooq.demo.modules.ServiceModule;
import org.jooq.demo.services.UserService;

import com.google.inject.Guice;
import com.google.inject.Injector;


public class App {
	
	private final static Injector INJECTOR = Guice.createInjector(new DaoModule(), new ServiceModule());

	public static void createTables() {
		Connection connection = null;
		try {
			CachedConfiguration conf = INJECTOR.getInstance(CachedConfiguration.class);
			connection = conf.connectionProvider().acquire();
			RunScript.execute(connection, new InputStreamReader(App.class.getResourceAsStream("/database_init.sql")));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
			}
		}
	}
	
    public static void main( String[] args ) {
    	createTables();
        UserService userService = INJECTOR.getInstance(UserService.class);
        
        // creation
        User userCreated = userService.create("Demo", "jOOQ", "demo@jooq.org", "jooq", "demo");
        System.out.println("User " + userCreated.getUserId() + " created");
        
        // authentication
        try {
			userService.authenticate("jooq", "falsepw");
		} catch (AuthentificationException e) {
			System.out.println(e.getMessage());
		}
        try {
			User userAuth = userService.authenticate("jooq", "demo");
			System.out.println("User " + userAuth.getUserId() + " logged in");
		} catch (AuthentificationException e) {
			System.out.println(e.getMessage());
		}
        
        // search
        System.out.println(userService.search("test", "test").size() + " user(s) found");
        System.out.println(userService.search("de", "a").size() + " user(s) found");
        System.out.println(userService.search("de", null).size() + " user(s) found");
        System.out.println(userService.search("de", "jo").size() + " user(s) found");
        System.out.println(userService.search("demo", "jooq").size() + " user(s) found");
        
        // search use cache
		long start = System.currentTimeMillis();
		for(int i=0; i<100000;i++) {
			List<User> users = userService.search("demo", "jooq");
			if(users.size() != 1 || !"demo@jooq.org".equals(users.get(0).getEmail())) {
				throw new RuntimeException("Oops");
			}
			if(i%10000 == 0 && i != 0) {
				System.out.println( i + " query results fetched from cache");
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("Total time : " + (end -start));
    }
    
}
