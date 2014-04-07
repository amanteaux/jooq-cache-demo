/**
 * This class is generated by jOOQ
 */
package org.jooq.demo.db.generated.tables.pojos;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.3.1" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UserPojo implements org.jooq.demo.db.generated.tables.interfaces.IUser {

	private static final long serialVersionUID = 1934074742;

	private java.lang.Long   userId;
	private java.lang.String firstname;
	private java.lang.String lastname;
	private java.lang.String email;
	private java.lang.String username;
	private java.lang.String password;

	public UserPojo() {}

	public UserPojo(
		java.lang.Long   userId,
		java.lang.String firstname,
		java.lang.String lastname,
		java.lang.String email,
		java.lang.String username,
		java.lang.String password
	) {
		this.userId = userId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.username = username;
		this.password = password;
	}

	@Override
	public java.lang.Long getUserId() {
		return this.userId;
	}

	@Override
	public void setUserId(java.lang.Long userId) {
		this.userId = userId;
	}

	@Override
	public java.lang.String getFirstname() {
		return this.firstname;
	}

	@Override
	public void setFirstname(java.lang.String firstname) {
		this.firstname = firstname;
	}

	@Override
	public java.lang.String getLastname() {
		return this.lastname;
	}

	@Override
	public void setLastname(java.lang.String lastname) {
		this.lastname = lastname;
	}

	@Override
	public java.lang.String getEmail() {
		return this.email;
	}

	@Override
	public void setEmail(java.lang.String email) {
		this.email = email;
	}

	@Override
	public java.lang.String getUsername() {
		return this.username;
	}

	@Override
	public void setUsername(java.lang.String username) {
		this.username = username;
	}

	@Override
	public java.lang.String getPassword() {
		return this.password;
	}

	@Override
	public void setPassword(java.lang.String password) {
		this.password = password;
	}

	// -------------------------------------------------------------------------
	// FROM and INTO
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void from(org.jooq.demo.db.generated.tables.interfaces.IUser from) {
		setUserId(from.getUserId());
		setFirstname(from.getFirstname());
		setLastname(from.getLastname());
		setEmail(from.getEmail());
		setUsername(from.getUsername());
		setPassword(from.getPassword());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <E extends org.jooq.demo.db.generated.tables.interfaces.IUser> E into(E into) {
		into.from(this);
		return into;
	}
}
