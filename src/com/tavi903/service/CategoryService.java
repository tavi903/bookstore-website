package com.tavi903.service;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.tavi903.dao.CategoryDAO;
import com.tavi903.entity.Category;

@Singleton
public class CategoryService extends BaseService<Category> {

	@Inject
	public CategoryService(CategoryDAO categoryDAO) {
		super(categoryDAO);
	}

}
