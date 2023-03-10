package com.tavi903.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ObjectUtils;

import com.tavi903.action.BaseAction;
import com.tavi903.factory.AdminActionHandlerFactory;

@MultipartConfig
@WebServlet("/admin/multipart")
public class AdminMultipartController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private AdminActionHandlerFactory adminActionHandlerFactory;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException  {
		
		String action = 
				ObjectUtils.defaultIfNull(request.getParameter("action"), "basic_home");
		
		BaseAction baseAction = adminActionHandlerFactory.create(action);
		baseAction.perform(request, response);
		
	}

}
