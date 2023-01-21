package action.book;

import static utils.ProjectUtils.getFromCache;
import static utils.ProjectUtils.loadJsp;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import action.BaseAction;
import config.ApplicationConfig;
import entity.Book;
import entity.Category;
import service.BookService;
import service.CategoryService;
import utils.BookStoreException;

public class EditBookAction implements BaseAction {
	
	private final BookService bookService;
	private final CategoryService categoryService;
	
	@Inject
	public EditBookAction(BookService bookService, CategoryService categoryService) {
		this.bookService = bookService;
		this.categoryService = categoryService;
	}

	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int pageSize = ApplicationConfig.BOOK_PAGE_SIZE;
		
		Category category = categoryService.find(Long.parseLong(request.getParameter("category"))).get();
		
		if(request.getParameter("publishDate").equals("")) {
			throw new BookStoreException("Publish Date can not be null.");
		}
		
		Book book = Book.builder()
				.bookId(Long.parseLong(request.getParameter("bookId")))
				.title(request.getParameter("title"))
				.author(request.getParameter("author"))
				.category(category)
				.description(request.getParameter("description"))
				.isbn(request.getParameter("isbn"))
				.publishDate(Date.valueOf(request.getParameter("publishDate")))
				.price(Float.valueOf(request.getParameter("price")))
				.lastUpdate(Timestamp.valueOf(request.getParameter("lastUpdate")))
				.build();
		
		Part part = request.getPart("bookImage");
		
		if (Objects.nonNull(part)) {

			byte[] imageBytes = new byte[(int) part.getSize()];
			InputStream inputStream = part.getInputStream();
			inputStream.read(imageBytes);
			book.setImage(imageBytes);

		}

		bookService.update(book);
		
		/* Add Request Attributes */

		request.setAttribute("message", "The book has been updated.");
		long totalBooks = (long) getFromCache(request, "totalBooks");
		List<Book> books = bookService.findAll(1, pageSize);
		request.setAttribute("currentPage", 1);
		request.setAttribute("books", books);
		request.setAttribute("minPrice", (float) getFromCache(request, "minPrice"));
		request.setAttribute("maxPrice", (float) getFromCache(request, "maxPrice"));
		request.setAttribute("totalPages",
				totalBooks % pageSize > 0 ? (totalBooks / pageSize + 1) : (totalBooks / pageSize));
		
		/* ********************** */
				
		loadJsp(request, response, "view/search_books.jsp");
		
	}

}
