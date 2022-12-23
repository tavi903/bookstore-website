package config;

import java.util.Objects;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class PersistenceConfig {

	private static String persistenceUnit;
	private static EntityManagerFactory entityManagerFactory;
	
	public static void setPersistenceUnit(String persistenceUnit) {
		PersistenceConfig.persistenceUnit = persistenceUnit;
	}

	public static EntityManagerFactory getEntityManagerFactory() {
		if(Objects.isNull(entityManagerFactory)) setEntityManagerFactory(persistenceUnit);
		return entityManagerFactory;
	}

	private static void setEntityManagerFactory(String persistenceUnit) {
		entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnit);
	}

}
