package action.book;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Singleton;
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

import static utils.ProjectUtils.*;

@Singleton
public class CreateBookAction implements BaseAction {
	
	private final BookService bookService;
	private final CategoryService categoryService;
	
	@Inject
	public CreateBookAction(BookService bookService, CategoryService categoryService) {
		this.bookService     = bookService;
		this.categoryService = categoryService;
	}

	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int pageSize = ApplicationConfig.BOOK_PAGE_SIZE;
		
		Category category = categoryService.find(Long.parseLong(request.getParameter("category"))).get();

		Book book = Book.builder()
				.title(request.getParameter("title"))
				.author(request.getParameter("author"))
				.category(category)
				.description("...")
				.isbn("42")
				.publishDate(Date.valueOf("2022-12-11"))
				.build();
		
		Part part = request.getPart("bookImage");
		
		if (Objects.nonNull(part)) {

			byte[] imageBytes = new byte[(int) part.getSize()];
			InputStream inputStream = part.getInputStream();
			inputStream.read(imageBytes);
			book.setImage(imageBytes);

		}

		bookService.create(book);
		
		request.setAttribute("message", "The book has been created.");
		long totalBooks = (long) getFromCache(request, "totalBooks");
		List<Book> books = bookService.findAll(1, pageSize);
		request.setAttribute("currentPage", 1);
		request.setAttribute("books", books);
		request.setAttribute("totalPages",
				totalBooks % pageSize > 0 ? (totalBooks / pageSize + 1) : (totalBooks / pageSize));
		
		loadJsp(request, response, "view/search_books.jsp");

	}

}
