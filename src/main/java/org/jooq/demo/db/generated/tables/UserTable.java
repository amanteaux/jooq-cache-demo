/**
 * This class is generated by jOOQ
 */
package org.jooq.demo.db.generated.tables;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.3.1" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UserTable extends org.jooq.impl.TableImpl<org.jooq.demo.db.generated.tables.records.UserRecord> {

	private static final long serialVersionUID = 2041378966;

	/**
	 * The singleton instance of <code>EXAMPLE.USER</code>
	 */
	public static final org.jooq.demo.db.generated.tables.UserTable USER = new org.jooq.demo.db.generated.tables.UserTable();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.jooq.demo.db.generated.tables.records.UserRecord> getRecordType() {
		return org.jooq.demo.db.generated.tables.records.UserRecord.class;
	}

	/**
	 * The column <code>EXAMPLE.USER.USER_ID</code>.
	 */
	public final org.jooq.TableField<org.jooq.demo.db.generated.tables.records.UserRecord, java.lang.Long> USER_ID = createField("USER_ID", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

	/**
	 * The column <code>EXAMPLE.USER.FIRSTNAME</code>.
	 */
	public final org.jooq.TableField<org.jooq.demo.db.generated.tables.records.UserRecord, java.lang.String> FIRSTNAME = createField("FIRSTNAME", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

	/**
	 * The column <code>EXAMPLE.USER.LASTNAME</code>.
	 */
	public final org.jooq.TableField<org.jooq.demo.db.generated.tables.records.UserRecord, java.lang.String> LASTNAME = createField("LASTNAME", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

	/**
	 * The column <code>EXAMPLE.USER.EMAIL</code>.
	 */
	public final org.jooq.TableField<org.jooq.demo.db.generated.tables.records.UserRecord, java.lang.String> EMAIL = createField("EMAIL", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

	/**
	 * The column <code>EXAMPLE.USER.USERNAME</code>.
	 */
	public final org.jooq.TableField<org.jooq.demo.db.generated.tables.records.UserRecord, java.lang.String> USERNAME = createField("USERNAME", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

	/**
	 * The column <code>EXAMPLE.USER.PASSWORD</code>.
	 */
	public final org.jooq.TableField<org.jooq.demo.db.generated.tables.records.UserRecord, java.lang.String> PASSWORD = createField("PASSWORD", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

	/**
	 * Create a <code>EXAMPLE.USER</code> table reference
	 */
	public UserTable() {
		this("USER", null);
	}

	/**
	 * Create an aliased <code>EXAMPLE.USER</code> table reference
	 */
	public UserTable(java.lang.String alias) {
		this(alias, org.jooq.demo.db.generated.tables.UserTable.USER);
	}

	private UserTable(java.lang.String alias, org.jooq.Table<org.jooq.demo.db.generated.tables.records.UserRecord> aliased) {
		this(alias, aliased, null);
	}

	private UserTable(java.lang.String alias, org.jooq.Table<org.jooq.demo.db.generated.tables.records.UserRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, org.jooq.demo.db.generated.ExampleTable.EXAMPLE, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<org.jooq.demo.db.generated.tables.records.UserRecord> getPrimaryKey() {
		return org.jooq.demo.db.generated.Keys.CONSTRAINT_2;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<org.jooq.demo.db.generated.tables.records.UserRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<org.jooq.demo.db.generated.tables.records.UserRecord>>asList(org.jooq.demo.db.generated.Keys.CONSTRAINT_2, org.jooq.demo.db.generated.Keys.UNIQUE_USER_USERNAME);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.demo.db.generated.tables.UserTable as(java.lang.String alias) {
		return new org.jooq.demo.db.generated.tables.UserTable(alias, this);
	}

	/**
	 * Rename this table
	 */
	public org.jooq.demo.db.generated.tables.UserTable rename(java.lang.String name) {
		return new org.jooq.demo.db.generated.tables.UserTable(name, null);
	}
}
