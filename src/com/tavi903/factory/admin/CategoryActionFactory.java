package com.tavi903.factory.admin;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.tavi903.action.BaseAction;
import com.tavi903.action.category.CreateCategoryAction;
import com.tavi903.action.category.EditCategoryAction;
import com.tavi903.action.category.GetAllCategoriesAction;
import com.tavi903.action.category.GetCategoryAction;
import com.tavi903.config.ActionConfig;

@Singleton
public class CategoryActionFactory {
	
	private final CreateCategoryAction createCategoryAction;
	private final EditCategoryAction editCategoryAction;
	private final GetCategoryAction getCategoryAction;
	private final GetAllCategoriesAction getAllCategoriesAction;
	
	@Inject
	public CategoryActionFactory(
			CreateCategoryAction createCategoryAction,
			EditCategoryAction editCategoryAction,
			GetCategoryAction getCategoryAction,
			GetAllCategoriesAction getAllCategoriesAction) {
		super();
		this.createCategoryAction = createCategoryAction;
		this.editCategoryAction = editCategoryAction;
		this.getCategoryAction = getCategoryAction;
		this.getAllCategoriesAction = getAllCategoriesAction;
	}
	
	public BaseAction create(String action) {
		
		if(action.equals(ActionConfig.CREATE_CATEGORY)) {
			return createCategoryAction;
		} else if(action.equals(ActionConfig.EDIT_CATEGORY)) {
			return editCategoryAction;
		} else if(action.equals(ActionConfig.GET_CATEGORY)) {
			return getCategoryAction;
		} else if(action.equals(ActionConfig.GET_ALL_CATEGORIES)) {
			return getAllCategoriesAction;
		} else {
			throw new RuntimeException("Action " + action + " not present.");
		}
		
	}

}
