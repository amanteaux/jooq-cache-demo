package org.jooq.demo;

import java.util.List;

import org.jooq.demo.db.beans.User;
import org.jooq.demo.services.UserService;

public class MicroBenchmark {

	public static void main(String[] args) {
		System.out.println("one row result query benchmark");
		oneRowResult();
		
		System.out.println("thousand rows result query benchmark");
		thousandRowsResult();
	}


	private static void oneRowResult() {
		App app = new App();
        UserService userService = app.injector().getInstance(UserService.class);
        
        // one insert
        userService.create("Demo", "jOOQ", "demo@jooq.org", "jooq", "demo");

        // warm up
		for(int i=0; i<20000;i++) {
			userService.search("demo", "jooq");
		}
		
		// micro benchmark
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
	
	private static void thousandRowsResult() {
		App app = new App();
        UserService userService = app.injector().getInstance(UserService.class);
        
        // 1000 inserts
        for(int i=0; i<1000; i++) {
        	userService.create("Demo", "jOOQ", "demo@jooq.org", "jooq" + i, "demo");
        }

        // warm up
		for(int i=0; i<2000;i++) {
			userService.search(null, null);
		}
		
		// micro benchmark
		long start = System.currentTimeMillis();
		for(int i=0; i<10000;i++) {
			List<User> users = userService.search(null, null);
			if(users.size() != 1000 || !"demo@jooq.org".equals(users.get(0).getEmail())) {
				throw new RuntimeException("Oops");
			}
			if(i%1000 == 0 && i != 0) {
				System.out.println( i + " query results fetched from cache");
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("Total time : " + (end -start));
	}

}
