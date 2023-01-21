package config;

public class ActionConfig {
	
	/* Basic Actions */
	
	public static final String HOME     = "home";
	public static final String LOGIN    = "login";
	public static final String LOGOUT   = "logout";
	public static final String REDIRECT = "redirect";
	
	/* ----------------------- */
	
	/* User Actions */
	
	public static final String CREATE_USER   = "create_user";
	public static final String EDIT_USER     = "edit_user";
	public static final String GET_USER      = "get_user";
	public static final String DELETE_USER   = "delete_user";
	public static final String GET_ALL_USERS = "all_users";

	/* ------------------------ */
	
	/* Category Actions */
	
	public static final String CREATE_CATEGORY     = "create_category";
	public static final String EDIT_CATEGORY       = "edit_category";
	public static final String GET_CATEGORY        = "get_category";
	public static final String GET_ALL_CATEGORIES  = "all_categories";

	/* ------------------------ */
	
	/* Books Actions */
	
	public static final String CREATE_BOOK  = "create_book";
	public static final String EDIT_BOOK    = "edit_book";
	public static final String GET_BOOK     = "get_book";
	public static final String SEARCH_BOOKS = "search_books";
	
	/* ------------------------ */

	public static boolean isBasicAction(String action) {
		if(action.equals(HOME)
				|| action.equals(LOGIN)
				|| action.equals(LOGOUT)
				|| action.startsWith(REDIRECT))
			return true;
		else return false;
	}
	
	public static boolean isUserAction(String action) {
		if(action.equals(CREATE_USER)
				|| action.equals(EDIT_USER)
				|| action.equals(GET_USER)
				|| action.equals(DELETE_USER)
				|| action.equals(GET_ALL_USERS))
			return true;
		else return false;
	}
	
	public static boolean isCategoryAction(String action) {
		if(action.equals(CREATE_CATEGORY)
				|| action.equals(EDIT_CATEGORY)
				|| action.equals(GET_CATEGORY)
				|| action.equals(GET_ALL_CATEGORIES))
			return true;
		else return false;
	}
	
	public static boolean isBookAction(String action) {
		if(action.equals(CREATE_BOOK)
				|| action.equals(EDIT_BOOK)
				|| action.equals(GET_BOOK)
				|| action.equals(SEARCH_BOOKS))
			return true;
		else return false;
	}
	
}
