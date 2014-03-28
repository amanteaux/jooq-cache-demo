package org.jooq.demo.services;

import static org.fest.assertions.Assertions.assertThat;

import org.jooq.cache.impl.DefaultCachedConfiguration;
import org.jooq.demo.db.beans.User;
import org.jooq.demo.db.daos.UserDao;
import org.jooq.demo.exception.AuthentificationException;
import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Objects;

public class UserServiceTest {
	
	private UserService userService;
	
	@Before
	public void init() {
		userService = new UserService(mockUserDao(), mockPasswordService());
	}

	@Test
	public void should_return_the_authenticated_user_if_the_login_and_password_are_correct() throws AuthentificationException {
		assertThat(userService.authenticate("testLogin", "testPassword")).isNotNull();
	}
	
	@Test(expected=AuthentificationException.class)
	public void should_throw_an_exception_if_the_password_does_not_match() throws AuthentificationException {
		userService.authenticate("testLogin", "wrongPassword");
	}
	
	@Test(expected=AuthentificationException.class)
	public void should_throw_an_exception_if_the_login_does_not_exist() throws AuthentificationException {
		userService.authenticate("wrongLogin", "passwordWillNotBeUsed");
	}
	
	// utils
	
	private UserDao mockUserDao() {
		return new UserDao(new DefaultCachedConfiguration()) {
			@Override
			public User fetchOneByUsername(String value) {
				if("testLogin".equals(value)) {
					return new User() {
						private static final long serialVersionUID = 1L;

						@Override
						public String getPassword() {
							return "testPassword";
						}
					};
				}
				return null;
			}
		};
	}
	
	private PasswordService mockPasswordService() {
		return new PasswordService() {
			@Override
			public boolean checkPassword(String candidate, String hashed) {
				return Objects.equal(candidate, hashed);
			}
		};
	}
	
}
