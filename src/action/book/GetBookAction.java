package action.book;

import static utils.ProjectUtils.loadJsp;
import static utils.ProjectUtils.loadMessageJsp;

import java.io.IOException;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.BaseAction;
import entity.Book;
import service.BookService;

@Singleton
public class GetBookAction implements BaseAction {
	
	private final BookService bookService;
	
	@Inject
	public GetBookAction(BookService bookService) {
		this.bookService = bookService;
	}

	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Optional<Book> bookMaybe = bookService.find(Long.parseLong(request.getParameter("id")));
		
		if (bookMaybe.isPresent()) {
			request.setAttribute("book", bookMaybe.get());
			loadJsp(request, response, "view/book_form.jsp");
		} else {
			loadMessageJsp(request, response, "Book cannot be found.");
		}
		
	}
	
	

}
