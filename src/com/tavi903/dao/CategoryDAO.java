package com.tavi903.dao;

import javax.inject.Singleton;

import com.tavi903.config.ApplicationConfig;
import com.tavi903.entity.Category;

@Singleton
public class CategoryDAO extends BaseDAO<Category> {

	public CategoryDAO() {
		super(ApplicationConfig.entityManagerFactory, Category.class);
	}

}
