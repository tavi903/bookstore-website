package com.tavi903.dao;

import javax.inject.Singleton;

import com.tavi903.config.PersistenceConfig;
import com.tavi903.entity.Category;

@Singleton
public class CategoryDAO extends GenericDAO<Category> {

	public CategoryDAO() {
		super(PersistenceConfig.getEntityManagerFactory(), Category.class);
	}

}