package org.jooq.demo.db;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import org.jooq.CachedConfiguration;
import org.jooq.ConfigurationExtended;
import org.jooq.DAO;
import org.jooq.impl.CachedDAOImpl;
import org.jooq.util.Database;
import org.jooq.util.DefaultExtendedGeneratorStrategy;
import org.jooq.util.ExtendedGeneratorStrategy;
import org.jooq.util.ExtendedGeneratorStrategy.ModeExtended;
import org.jooq.util.GeneratorStrategy.Mode;
import org.jooq.util.JavaExtendedGenerator;
import org.jooq.util.JavaWriter;
import org.jooq.util.TableDefinition;
import org.jooq.util.h2.H2Database;
import org.jooq.util.jaxb.CustomType;
import org.jooq.util.jaxb.EnumType;
import org.jooq.util.jaxb.ForcedType;
import org.jooq.util.jaxb.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersistenceGenerator extends JavaExtendedGenerator {
	
	private static final Logger logger = LoggerFactory.getLogger(PersistenceGenerator.class);

	public PersistenceGenerator(final ExtendedGeneratorStrategy extendedGeneratorStrategy) {
		super(extendedGeneratorStrategy);
	}

	protected static Connection getConnection() {
		try {
			Class.forName("org.h2.Driver");
			return DriverManager.getConnection("jdbc:h2:mem:example;INIT=CREATE SCHEMA IF NOT EXISTS EXAMPLE\\;SET SCHEMA EXAMPLE", "sa", "");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Connection initSqlSchema() {
		try {
			Connection connection = getConnection();
			DbInit.init(connection);
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(final String[] args) {
		Connection connection = initSqlSchema();

		final PersistenceGenerator pg = new PersistenceGenerator(new DefaultExtendedGeneratorStrategy() {
			@SuppressWarnings({ "unchecked" })
			@Override
			public Class<? extends DAO<?,?,?>> getSuperDao() {
				return (Class<? extends DAO<?, ?, ?>>) CachedDAOImpl.class;
			}

			@Override
			public Class<? extends ConfigurationExtended> getSuperDaoConfiguration() {
				return CachedConfiguration.class;
			}
		});
		pg.setGenerateDaos(true);
		pg.setGenerateInterfaces(true);
		pg.setGenerateRelations(true);
		pg.setGeneratePojos(true);
		pg.setGenerateInstanceFields(true);
		pg.getExtendedStrategy().setInstanceFields(true);
		pg.getExtendedStrategy().setChildEntitiesTargetDirectory("org.jooq.demo.db");

		// target
		pg.setTargetDirectory("src/main/java");
		pg.setTargetPackage("org.jooq.demo.db.generated");

		// schéma
		final Schema schema = new Schema();
		schema.setInputSchema("EXAMPLE");
		schema.setOutputSchema("EXAMPLE");

		// database
		final Database database = new H2Database();
		database.setConnection(connection);
		database.setConfiguredSchemata(Arrays.asList(schema));
		database.setIncludes(new String[] { ".*" });
		database.setExcludes(new String[] { "" });
		database.setConfiguredCustomTypes(new ArrayList<CustomType>());
		database.setConfiguredEnumTypes(new ArrayList<EnumType>());
		database.setConfiguredForcedTypes(new ArrayList<ForcedType>());
		database.setRecordVersionFields(new String[] { "" });
		database.setRecordTimestampFields(new String[] { "" });

		pg.generateWithChildEntities(database);
	}
	
	// Guice injection for the dao

	@Override
	protected void generateDaoOnce(final TableDefinition table) {
		final String className = getExtendedStrategy().getJavaClassName(table, ModeExtended.DAO);

		final File daoFile = getExtendedStrategy().getFile(table, ModeExtended.DAO);
		if (daoFile.exists()) {
			logger.info("The child DAO is already generated", className);
		} else {
			logger.info("Generating child DAO", getExtendedStrategy().getFileName(table, ModeExtended.DAO));

			final String abstractDao = getExtendedStrategy().getFullJavaClassName(table, Mode.DAO);
			final String beanChild = getExtendedStrategy().getFullJavaClassName(table, ModeExtended.BEAN);
			final String tableIdentifier = getStrategy().getFullJavaIdentifier(table);

			JavaWriter out = new JavaWriter(daoFile);
			printPackage(out, table, ModeExtended.DAO);
			out.println("import javax.inject.Inject;");
			out.println("import javax.inject.Singleton;");
			printExtendedClassJavadoc(out, table);
			
			out.println("@Singleton");
			out.println("public class %s extends %s<%s> {", className, abstractDao, beanChild);

			// Default constructor
			// -------------------
			out.tab(1).javadoc("Create a new %s with an attached configuration", className);
			out.tab(1).println("@Inject");
			out.tab(1).println("public %s(%s configuration) {", className, getExtendedStrategy().getSuperDaoConfiguration());
			out.tab(2).println("super(%s, %s.class, configuration);", tableIdentifier, beanChild);
			out.tab(1).println("}");

			out.println("}");
			out.close();
		}
	}

}
