package org.jooq.demo.db.daos;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * This class is generated once by jOOQ Codegen Extended.<br>
 * It will not be overriden by another code generation : you can freely change it.
 */
@Singleton
public class GroupDao extends org.jooq.demo.db.generated.tables.daos.AbstractGroupDao<org.jooq.demo.db.beans.Group> {

	/**
	 * Create a new GroupDao with an attached configuration
	 */
	@Inject
	public GroupDao(org.jooq.CachedConfiguration configuration) {
		super(org.jooq.demo.db.generated.tables.GroupTable.GROUP, org.jooq.demo.db.beans.Group.class, configuration);
	}
}
