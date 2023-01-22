package com.tavi903.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@FunctionalInterface
public interface BaseAction {

	public void perform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;
	
}
