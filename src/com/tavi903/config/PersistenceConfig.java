package com.tavi903.config;

import java.util.Objects;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class PersistenceConfig {

	private static String persistenceUnit = "BookStoreWebsite";
	private static EntityManagerFactory entityManagerFactory;
	
	public static void setPersistenceUnit(String persistenceUnit) {
		PersistenceConfig.persistenceUnit = persistenceUnit;
	}

	public static EntityManagerFactory getEntityManagerFactory() {
		if(Objects.isNull(entityManagerFactory)) setEntityManagerFactory();
		return entityManagerFactory;
	}

	private static void setEntityManagerFactory() {
		entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnit);
	}

}
