package dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import config.PersistenceConfig;
import entity.Book;

@Singleton
public class BookDAO extends GenericDAO<Book> {

	@Inject
	public BookDAO(PersistenceConfig persistenceConfig) {
		super(persistenceConfig.getEntityManagerFactory().createEntityManager(), Book.class);
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

}
