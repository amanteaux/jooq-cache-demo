/**
 * This class is generated by jOOQ
 */
package org.jooq.demo.db.generated;

/**
 * This class is generated by jOOQ.
 *
 * A class modelling foreign key relationships between tables of the <code>EXAMPLE</code> 
 * schema
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.2.0" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

	// -------------------------------------------------------------------------
	// IDENTITY definitions
	// -------------------------------------------------------------------------


	// -------------------------------------------------------------------------
	// UNIQUE and PRIMARY KEY definitions
	// -------------------------------------------------------------------------

	public static final org.jooq.UniqueKey<org.jooq.demo.db.generated.tables.records.UserRecord> CONSTRAINT_2 = UniqueKeys0.CONSTRAINT_2;
	public static final org.jooq.UniqueKey<org.jooq.demo.db.generated.tables.records.UserRecord> UNIQUE_USER_USERNAME = UniqueKeys0.UNIQUE_USER_USERNAME;
	public static final org.jooq.UniqueKey<org.jooq.demo.db.generated.tables.records.GroupRecord> CONSTRAINT_4 = UniqueKeys0.CONSTRAINT_4;

	// -------------------------------------------------------------------------
	// FOREIGN KEY definitions
	// -------------------------------------------------------------------------


	// -------------------------------------------------------------------------
	// [#1459] distribute members to avoid static initialisers > 64kb
	// -------------------------------------------------------------------------

	private static class UniqueKeys0 extends org.jooq.impl.AbstractKeys {
		public static final org.jooq.UniqueKey<org.jooq.demo.db.generated.tables.records.UserRecord> CONSTRAINT_2 = createUniqueKey(org.jooq.demo.db.generated.tables.UserTable.USER, org.jooq.demo.db.generated.tables.UserTable.USER.USER_ID);
		public static final org.jooq.UniqueKey<org.jooq.demo.db.generated.tables.records.UserRecord> UNIQUE_USER_USERNAME = createUniqueKey(org.jooq.demo.db.generated.tables.UserTable.USER, org.jooq.demo.db.generated.tables.UserTable.USER.USERNAME);
		public static final org.jooq.UniqueKey<org.jooq.demo.db.generated.tables.records.GroupRecord> CONSTRAINT_4 = createUniqueKey(org.jooq.demo.db.generated.tables.GroupTable.GROUP, org.jooq.demo.db.generated.tables.GroupTable.GROUP.GROUP_ID);
	}
}
