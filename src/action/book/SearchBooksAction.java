package action.book;

import java.io.IOException;
import java.sql.Date;
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
import static org.apache.commons.lang3.StringUtils.isBlank;

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
		request.setAttribute("minPrice", bookService.minPrice());
		request.setAttribute("maxPrice", bookService.maxPrice());
		request.setAttribute("books", books);
		request.setAttribute("totalPages",
				totalBooks % pageSize > 0 ? (totalBooks / pageSize + 1) : (totalBooks / pageSize));
		
		/* ********************** */
		
		loadJsp(request, response, "view/search_books.jsp");

	}
	
	// null, "" -> null
	// 
	private void addParameters(HttpServletRequest request, Map<String, Object> params) {
		params.put("lowerPrice", isBlank(request.getParameter("lowerPrice")) ? null : Float.parseFloat(request.getParameter("lowerPrice")));
		params.put("upperPrice", isBlank(request.getParameter("upperPrice")) ? null : Float.parseFloat(request.getParameter("upperPrice")));
		params.put("title", isBlank(request.getParameter("title")) ? null : request.getParameter("title"));
		params.put("author", isBlank(request.getParameter("author")) ? null : request.getParameter("author"));
		params.put("category", isBlank(request.getParameter("category")) ? null : Long.parseLong(request.getParameter("category")));
		params.put("lowerPublishDate", isBlank(request.getParameter("lowerPublishDate")) ? null : Date.valueOf(request.getParameter("lowerPublishDate")));
		params.put("upperPublishDate", isBlank(request.getParameter("upperPublishDate")) ? null : Date.valueOf(request.getParameter("upperPublishDate")));
	}
	
}
