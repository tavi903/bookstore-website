package utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import cache.BookStoreWebsiteCache;

public class ProjectUtils {
	
	public static void loadJsp(ServletRequest request, ServletResponse response, String targetJsp)
			throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(targetJsp);
		requestDispatcher.forward(request, response);
	}
	
	public static void loadMessageJsp(ServletRequest request, ServletResponse response, String message) throws ServletException, IOException {
		request.setAttribute("message", message);
		loadJsp(request, response, "view/message.jsp");
	}
	
	public static Object getFromCache(ServletRequest request, String key) {
		BookStoreWebsiteCache cache = (BookStoreWebsiteCache) request.getServletContext().getAttribute("cache");
		return cache.get(key);
	}
	
	public static Object nullIf(Object obj, Object other) {
		if (Objects.isNull(obj) || obj.equals(other)) return null;
		else return obj;
	}
	
	public static <E> List<E> createList(E ... args) {
		List<E> result = new ArrayList<E>();
		for(E arg : args) {
			result.add(arg);
		}
		return result;
	}

}
