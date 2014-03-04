package org.jooq.demo.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.jooq.demo.db.beans.User;
import org.jooq.demo.db.daos.UserDao;
import org.jooq.demo.exception.AuthentificationException;

@Singleton
public class UserService {
	
	private final UserDao userDao;
	private final PasswordService passwordService;
	
	@Inject
	public UserService(UserDao userDao, PasswordService passwordService) {
		this.userDao = userDao;
		this.passwordService = passwordService;
	}
	
	public User create(String firstname, String lastname, String email, String username, String password) {
		User user = new User();
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setEmail(email);
		user.setUsername(username);
		user.setPassword(passwordService.hashPassword(password));
		
		userDao.insert(user);
		
		return user;
	}

	public List<User> search(String firstname, String lastname) {
		return userDao.search(firstname, lastname);
	}
	
	public User authenticate(String username, String password) throws AuthentificationException {
		User user = userDao.fetchOneByUsername(username);
		if(user == null) {
			throw new AuthentificationException("No user found");
		}
		if(passwordService.checkPassword(password, user.getPassword())) {
			return user;
		}
		throw new AuthentificationException("Password is incorrect");
	}

}
