package org.jooq.demo;

import org.jooq.demo.db.beans.User;
import org.jooq.demo.exception.AuthentificationException;
import org.jooq.demo.services.UserService;

public class Demo {

	public static void main(String[] args) {
		App app = new App();
        UserService userService = app.injector().getInstance(UserService.class);
        
        // creation
        User userCreated = userService.create("Demo", "jOOQ", "demo@jooq.org", "jooq", "demo");
        System.out.println("User " + userCreated.getUserId() + " created");
        
        // authentication
        try {
			User userAuth = userService.authenticate("jooq", "demo");
			System.out.println("User " + userAuth.getUserId() + " logged in");
		} catch (AuthentificationException e) {
			System.out.println(e.getMessage());
		}
        
        // search with caching
        System.out.println(userService.search("demo", "jooq").size() + " user(s) found");
	}
	
}
