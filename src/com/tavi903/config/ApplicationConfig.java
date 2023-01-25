package com.tavi903.config;

import java.util.logging.Logger;

public class ApplicationConfig {
	
	/* Pagination Configuration */
	
	public static final int USER_PAGE_SIZE     = 10;
	public static final int BOOK_PAGE_SIZE     = 10;
	public static final int CATEGORY_PAGE_SIZE = 4;
	
	/* ------------------------ */
	
	/* Cache */
	
	public static final int MINUTES_REFRESH = 10;

	/* ------------------------ */
	
	public static final Logger logger = Logger.getLogger("BookStore Logger");
	
}
