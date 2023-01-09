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
import utils.BookStoreException;

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
		
		if(request.getParameter("publishDate").equals("")) {
			throw new BookStoreException("Publish Date can not be null.");
		}
		
		Book book = Book.builder()
				.title(request.getParameter("title"))
				.author(request.getParameter("author"))
				.category(category)
				.description("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus luctus pulvinar elit posuere tempor. Maecenas dictum gravida convallis. Nullam porttitor sollicitudin eros, a laoreet augue pulvinar dignissim. Sed condimentum ullamcorper quam, semper volutpat ligula volutpat nec. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Aenean pharetra ligula eu placerat pharetra. Phasellus tincidunt posuere lorem vel ornare. Praesent dictum odio sit amet vestibulum lacinia. Proin malesuada.")
				.isbn(request.getParameter("isbn"))
				.publishDate(Date.valueOf(request.getParameter("publishDate")))
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
