package com.tavi903.config;

import java.util.logging.Logger;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ApplicationConfig {
	
	/* Pagination Configuration */
	
	public static final int USER_PAGE_SIZE     = 10;
	public static final int BOOK_PAGE_SIZE     = 10;
	public static final int CATEGORY_PAGE_SIZE = 4;
	
	/* ------------------------ */
	
	public static EntityManagerFactory entityManagerFactory =    
			Persistence.createEntityManagerFactory("bookstoredb");
	
	/* Cache */
	
	public static final int MINUTES_REFRESH = 10;

	/* ------------------------ */
	
	public static Logger logger = Logger.getLogger("Bookstore - Logger");;
	
}
