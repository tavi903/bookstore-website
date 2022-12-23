package dao;

import javax.inject.Singleton;

import config.PersistenceConfig;
import entity.Category;

@Singleton
public class CategoryDAO extends GenericDAO<Category> {

	public CategoryDAO() {
		super(PersistenceConfig.getEntityManagerFactory(), Category.class);
	}

}
