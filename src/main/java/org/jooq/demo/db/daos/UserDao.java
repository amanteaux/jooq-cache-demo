package org.jooq.demo.db.daos;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.jooq.SelectWhereStep;
import org.jooq.demo.db.beans.User;
import org.jooq.demo.db.generated.tables.UserTable;
import org.jooq.demo.db.generated.tables.records.UserRecord;

/**
 * This class is generated once by jOOQ Codegen Extended.<br>
 * It will not be overriden by another code generation : you can freely change it.
 */
@Singleton
public class UserDao extends org.jooq.demo.db.generated.tables.daos.AbstractUserDao<org.jooq.demo.db.beans.User> {

	/**
	 * Create a new UserDao with an attached configuration
	 */
	@Inject
	public UserDao(org.jooq.CachedConfiguration configuration) {
		super(org.jooq.demo.db.generated.tables.UserTable.USER, org.jooq.demo.db.beans.User.class, configuration);
	}

	public List<User> search(String firstname, String lastname) {
		SelectWhereStep<UserRecord> query = fromTable();
		if(firstname != null) {
			query.where(UserTable.USER.FIRSTNAME.likeIgnoreCase("%" + firstname + "%"));
		}
		if(lastname != null) {
			query.where(UserTable.USER.LASTNAME.likeIgnoreCase("%" + lastname + "%"));
		}
		return fetchCached(query).map(mapper());
	}
	
}
