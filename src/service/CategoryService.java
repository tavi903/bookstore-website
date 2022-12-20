package service;

import javax.inject.Inject;
import javax.inject.Singleton;

import dao.CategoryDAO;
import entity.Category;

@Singleton
public class CategoryService extends GenericService<Category> {

	@Inject
	public CategoryService(CategoryDAO categoryDAO) {
		super(categoryDAO);
	}

}
