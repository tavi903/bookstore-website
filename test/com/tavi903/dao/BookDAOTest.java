package com.tavi903.dao;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.tavi903.dao.BookDAO;
import com.tavi903.dao.CategoryDAO;
import com.tavi903.entity.Book;
import com.tavi903.entity.Category;

public class BookDAOTest {
	
	private static BookDAO bookDAO;
	private static EntityManager entityManager;
	private static List<Category> categories;
	private static List<Book> books;
	
	@BeforeClass
	public static void setUpClass() {
		
		bookDAO = new BookDAO();
		categories = new CategoryDAO().findAll();
		
		books = Arrays.asList(
				
				Book.builder()
				.title("The Light We Carry: Overcoming in Uncertain Times").author("Michelle Obama")
				.isbn("0593237463").publishDate(Date.valueOf("2022-11-15"))
				.category(categories.stream().filter(c -> c.getName().equals("Self Improvement"))
				.findFirst().get()).price(15.95f).build(),
				
				Book.builder()
				.title("Atomic Habits").author("James Clear")
				.isbn("0735211299").publishDate(Date.valueOf("2018-10-16"))
				.category(categories.stream().filter(c -> c.getName().equals("Self Improvement"))
				.findFirst().get()).price(11.98f).build(),
				
				Book.builder()
				.title("The Very Hungry Caterpillar").author("Eric Carle")
				.isbn("0399226907").publishDate(Date.valueOf("1994-03-23"))
				.category(categories.stream().filter(c -> c.getName().equals("Children"))
				.findFirst().get()).price(8.99f).build()
		
		);
			
	}
	
	@Before
	public void beginTransaction() {
		entityManager = bookDAO.getEntityManager();
		entityManager.getTransaction().begin();
	}
	
	@After
	public void rollbackTransaction() {
		entityManager.getTransaction().rollback();
	}
	
	@Test
	public void maxPrice() {
		assertEquals(books.stream().map(Book::getPrice).max((x,y) -> x > y ? 1 : -1).get(), bookDAO.maxPrice(), 0.001);
	}
	
	@Test
	public void minPrice() {
		assertEquals(books.stream().map(Book::getPrice).min((x,y) -> x > y ? 1 : -1).get(), bookDAO.minPrice(), 0.001);
	}
	
	@Test
	public void selectAuthors() {
		List<String> authorsExpected = books.stream().map(Book::getAuthor).sorted().collect(Collectors.toList());
		assertEquals(authorsExpected, bookDAO.selectAuthors());
	}
	
	@Test
	public void selectTitles() {
		List<String> titlesExpected = books.stream().map(Book::getTitle).sorted().collect(Collectors.toList());
		assertEquals(titlesExpected, bookDAO.selectTitles());
	}
	
	@Test
	public void countSearchBooksPublishedAfter_01_01_2015() {
		Map<String, Object> params = initSearchParams();
		params.put("lowerPublishDate", Date.valueOf("2015-01-01"));
		long valueExpected = books.stream().filter(b -> b.getPublishDate().after((Date) params.get("lowerPublishDate"))).count();
		assertEquals(valueExpected, bookDAO.countSearch(params));
	}
	
	@Test
	public void countSearchSelfImprovementBooks() {
		Map<String, Object> params = initSearchParams();
		params.put("category", categories.stream().filter(c -> c.getName().equals("Self Improvement")).findFirst().get());
		long valueExpected = books.stream().filter(b -> b.getCategory().equals((Category) params.get("category"))).count();
		assertEquals(valueExpected, bookDAO.countSearch(params));
	}
	
	private Map<String, Object> initSearchParams() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("lowerPrice", null);
		params.put("upperPrice", null);
		params.put("title", null);
		params.put("author", null);
		params.put("lowerPublishDate", null);
		params.put("upperPublishDate", null);
		params.put("category", null);
		return params;
	}

}
