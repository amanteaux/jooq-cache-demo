package org.jooq.demo.db.beans;


/**
 * This class is generated once by jOOQ Codegen Extended.<br>
 * It will not be overriden by another code generation : you can freely change it.
 */
public class User extends org.jooq.demo.db.generated.tables.pojos.UserPojo {

	private static final long serialVersionUID = -1442915362;

	public User() {
	}

	public User(Long userId, String firstname, String lastname, String email, String username, String password) {
		super(userId, firstname, lastname, email, username, password);
	}

	@Override
	public int hashCode() {
		return getUserId().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return obj != null && getUserId().equals(getUserId());
	}
	
}
