package action.book;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.BaseAction;
import config.ApplicationConfig;
import entity.Book;
import service.BookService;

import static utils.ProjectUtils.*;

public class SearchBooksAction implements BaseAction {
	
	private final BookService bookService;
	
	@Inject
	public SearchBooksAction(BookService bookService) {
		this.bookService = bookService;
	}

	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String, Object> params = new HashMap<>();
		addParameters(request, params);
		
		int pageSize = ApplicationConfig.BOOK_PAGE_SIZE;
		int page     = Integer.parseInt(request.getParameter("page"));

		long totalBooks  = bookService.countSearch(params);
		List<Book> books = bookService.search(params, page, pageSize);
		
		/* Add Request Attributes */

		request.setAttribute("params", params);
		request.setAttribute("currentPage", page);
		request.setAttribute("titles",  getFromCache(request, "titles"));
		request.setAttribute("authors", getFromCache(request, "authors"));
		request.setAttribute("books", books);
		request.setAttribute("totalPages",
				totalBooks % pageSize > 0 ? (totalBooks / pageSize + 1) : (totalBooks / pageSize));
		
		/* ********************** */
		
		loadJsp(request, response, "view/search_books.jsp");

	}
	
	private void addParameters(HttpServletRequest request, Map<String, Object> params) {
		params.put("lowerPrice", nullIf(request.getParameter("lowerPrice"), ""));
		params.put("upperPrice", nullIf(request.getParameter("upperPrice"), ""));
		params.put("title", nullIf(request.getParameter("title"), ""));
		params.put("author", nullIf(request.getParameter("author"), ""));
		params.put("category", nullIf(request.getParameter("category"), ""));
		params.put("lowerPublishDate", nullIf(request.getParameter("lowerPublishDate"), ""));
		params.put("upperPublishDate", nullIf(request.getParameter("upperPublishDate"), ""));
	}
	
}
