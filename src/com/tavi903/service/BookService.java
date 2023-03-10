package com.tavi903.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.tavi903.dao.BookDAO;
import com.tavi903.entity.Book;

@Singleton
public class BookService extends BaseService<Book> {

	private final BookDAO bookDAO;

	@Inject
	public BookService(BookDAO bookDAO) {
		super(bookDAO);
		this.bookDAO = bookDAO;
	}

	public long countSearch(Map<String, Object> parameters) {
		return bookDAO.countSearch(parameters);
	}

	public List<Book> search(Map<String, Object> parameters, int page, int pageSize) {
		return bookDAO.search(parameters, page, pageSize);
	}

	public List<String> selectTitles() {
		return bookDAO.selectTitles();
	}

	public List<String> selectAuthors() {
		return bookDAO.selectAuthors();
	}
	
	public float maxPrice() {
		return bookDAO.maxPrice();
	}
	
	public float minPrice() {
		return bookDAO.minPrice();
	}

}
