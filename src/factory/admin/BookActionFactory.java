package factory.admin;

import javax.inject.Inject;
import javax.inject.Singleton;

import action.BaseAction;
import action.book.CreateBookAction;
import action.book.EditBookAction;
import action.book.GetBookAction;
import action.book.SearchBooksAction;
import config.ActionConfig;

@Singleton
public class BookActionFactory {
	
	private final CreateBookAction createBookAction;
	private final GetBookAction getBookAction;
	private final EditBookAction editBookAction;
	private final SearchBooksAction searchBooksAction;
	
	@Inject
	public BookActionFactory(
			CreateBookAction createBookAction,
		    GetBookAction getBookAction,
		    EditBookAction editBookAction,
		    SearchBooksAction searchBooksAction
	) {
		this.createBookAction  = createBookAction;
		this.getBookAction     = getBookAction;
		this.editBookAction    = editBookAction;
		this.searchBooksAction = searchBooksAction;
	}
	
	public BaseAction create(String action) {
		
		if(action.equals(ActionConfig.CREATE_BOOK)) {
			return createBookAction;
		} else if (action.equals(ActionConfig.GET_BOOK)) {
			return getBookAction;
		} else if(action.equals(ActionConfig.EDIT_BOOK)) {
			return editBookAction;
		}
		else if (action.equals(ActionConfig.SEARCH_BOOKS)) {
			return searchBooksAction;
		} else {
			throw new RuntimeException("Action " + action + " not present.");
		}
		
	}

}
