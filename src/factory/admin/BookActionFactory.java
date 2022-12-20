package factory.admin;

import javax.inject.Inject;
import javax.inject.Singleton;

import action.BaseAction;
import action.book.CreateBookAction;
import action.book.SearchBooksAction;
import config.ActionConfig;

@Singleton
public class BookActionFactory {
	
	private final CreateBookAction createBookAction;
	private final SearchBooksAction searchBooksAction;
	
	@Inject
	public BookActionFactory(CreateBookAction createBookAction, SearchBooksAction searchBooksAction) {
		this.createBookAction = createBookAction;
		this.searchBooksAction = searchBooksAction;
	}
	
	public BaseAction create(String action) {
		
		if(action.equals(ActionConfig.CREATE_BOOK)) {
			return createBookAction;
		} else if (action.equals(ActionConfig.SEARCH_BOOKS)) {
			return searchBooksAction;
		} else {
			throw new RuntimeException("Action " + action + " not present.");
		}
		
	}

}
