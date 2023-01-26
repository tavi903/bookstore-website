package com.tavi903.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Singleton;

import com.tavi903.config.ApplicationConfig;
import com.tavi903.entity.Book;

@Singleton
public class BookDAO extends GenericDAO<Book> {

	public BookDAO() {
		super(ApplicationConfig.entityManagerFactory, Book.class);
	}

	public long countSearch(Map<String, Object> parameters) {
		return (long) super.findWithNamedQuery("Book.countSearch", parameters, false);
	}

	public List<Book> search(Map<String, Object> parameters, int page, int pageSize) {
		return (List<Book>) super.findWithNamedQuery("Book.search", parameters, page, pageSize, true);
	}

	public List<String> selectTitles() {
		return (List<String>) super.findWithNamedQuery("Book.selectTitles", true);
	}

	public List<String> selectAuthors() {
		return (List<String>) super.findWithNamedQuery("Book.selectAuthors", true);
	}
	
	public float maxPrice() {
		return (float) super.findWithNamedQuery("Book.maxPrice", false);
	}
	
	public float minPrice() {
		return (float) super.findWithNamedQuery("Book.minPrice", false);
	}

}
