package com.tavi903.utils;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.tavi903.cache.BookStoreWebsiteCache;

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
	
	public static String getUserLogged(ServletRequest request) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		return (String) httpServletRequest.getSession().getAttribute("userEmail");
	}
	
}