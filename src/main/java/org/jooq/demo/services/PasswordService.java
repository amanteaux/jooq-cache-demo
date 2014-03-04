package org.jooq.demo.services;

import javax.inject.Singleton;

import org.mindrot.jbcrypt.BCrypt;

@Singleton
public class PasswordService {
	
	private final static int BCRYPT_SALT_ROUND = 8;
	
	public String hashPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt(BCRYPT_SALT_ROUND));
	}

	public boolean checkPassword(String candidate, String hashed) {
		return BCrypt.checkpw(candidate, hashed);
	}

}
