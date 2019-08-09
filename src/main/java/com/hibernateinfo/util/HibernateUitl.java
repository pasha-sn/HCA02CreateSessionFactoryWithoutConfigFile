package com.hibernateinfo.util;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

/**
 * @author Pasha
 *
 */
public class HibernateUitl {
	private static StandardServiceRegistry standardServiceRegistry;
	private static SessionFactory sessionFactory;

	// Static block is executed when class loads
	// sessionFactory is very expensive object
	// static block is common for all clients
	// Runs every time when the instance of the class is created
	static {
		//Create Instance Object of StandardServiceRegistryBuilder
		StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

		//Hibernate settings equivalent to hibernate.cfg.xml's properties
		Map<String, String> dbSettings = new HashMap<>();
		dbSettings.put(Environment.URL, "jdbc:oracle:thin:@localhost:1521:XE");
		dbSettings.put(Environment.USER, "HitTheBooks");
		dbSettings.put(Environment.PASS, "oracle");
		dbSettings.put(Environment.DRIVER, "oracle.jdbc.driver.OracleDriver");
		dbSettings.put(Environment.DIALECT, "org.hibernate.dialect.Oracle10gDialect");

		//Apply database settings
		registryBuilder.applySettings(dbSettings);

		//Create standardServiceRegistry
		standardServiceRegistry = registryBuilder.build();

		//Create metadataSources
		MetadataSources metadataSources = new MetadataSources(standardServiceRegistry);

		//Create metadata
		Metadata metadata = metadataSources.getMetadataBuilder().build();

		//Create SessionFactory
		sessionFactory = metadata.getSessionFactoryBuilder().build();
	}

	// create static Utility Factory method to return sessionFactory
	// A static method belongs to the class rather than the object of a class.
	// A static method can be invoked without the need for creating an instance of a class. 
	// A static method can access static data member and can change the value of it.
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
