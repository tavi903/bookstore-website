package com.tavi903.action.book;

import static com.tavi903.utils.ProjectUtils.loadJsp;
import static com.tavi903.utils.ProjectUtils.loadMessageJsp;

import java.io.IOException;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tavi903.action.BaseAction;
import com.tavi903.entity.Book;
import com.tavi903.service.BookService;

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
