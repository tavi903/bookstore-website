package com.tavi903.factory;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.tavi903.action.BaseAction;
import com.tavi903.config.ActionConfig;
import com.tavi903.factory.admin.BasicActionFactory;
import com.tavi903.factory.admin.BookActionFactory;
import com.tavi903.factory.admin.CategoryActionFactory;
import com.tavi903.factory.admin.UserActionFactory;

@Singleton
public class AdminActionHandlerFactory {
	
	private final BasicActionFactory basicActionFactory;
	private final CategoryActionFactory categoryActionFactory;
	private final UserActionFactory userActionFactory;
	private final BookActionFactory bookActionFactory;
	
	@Inject
	public AdminActionHandlerFactory(
			BasicActionFactory basicActionFactory,
			CategoryActionFactory categoryActionFactory,
			UserActionFactory userActionFactory,
			BookActionFactory bookActionFactory) {
		this.basicActionFactory    = basicActionFactory;
		this.categoryActionFactory = categoryActionFactory;
		this.userActionFactory     = userActionFactory; 
		this.bookActionFactory     = bookActionFactory;
	}
	
	public BaseAction create(String action) {
		
		if(ActionConfig.isBasicAction(action)) {
			return basicActionFactory.create(action);
		} else if(ActionConfig.isCategoryAction(action)) {
			return categoryActionFactory.create(action);
		} else if(ActionConfig.isUserAction(action)) {
			return userActionFactory.create(action);
		} else if(ActionConfig.isBookAction(action)) {
			return bookActionFactory.create(action);
		} else {
			throw new RuntimeException("Action " + action + " not present.");
		}
		
	}

}
