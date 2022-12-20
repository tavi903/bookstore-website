package config;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import lombok.Getter;

@ApplicationScoped
public class PersistenceConfig {
	
	public static final boolean CLEAR_CACHE = true;

	@Getter
	private final EntityManagerFactory entityManagerFactory = 
		Persistence.createEntityManagerFactory("BookStoreWebsite");

}
