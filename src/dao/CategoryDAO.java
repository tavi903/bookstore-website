package dao;

import javax.inject.Inject;
import javax.inject.Singleton;

import config.PersistenceConfig;
import entity.Category;

@Singleton
public class CategoryDAO extends GenericDAO<Category> {

	@Inject
	public CategoryDAO(PersistenceConfig persistenceConfig) {
		super(persistenceConfig.getEntityManagerFactory().createEntityManager(), Category.class);
	}

}
