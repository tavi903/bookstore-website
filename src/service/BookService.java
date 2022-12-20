package service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import dao.BookDAO;
import entity.Book;

@Singleton
public class BookService extends GenericService<Book> {

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

}
