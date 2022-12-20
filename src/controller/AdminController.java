package controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ObjectUtils;

import action.BaseAction;
import factory.AdminActionHandlerFactory;

@WebServlet("/admin/view")
public class AdminController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private AdminActionHandlerFactory adminActionHandlerFactory;

	public AdminController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = 
				ObjectUtils.defaultIfNull(request.getParameter("action"), "basic_home");
		
		BaseAction baseAction = adminActionHandlerFactory.create(action);
		baseAction.perform(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException  {
		
		String action = 
				ObjectUtils.defaultIfNull(request.getParameter("action"), "basic_home");
		
		BaseAction baseAction = adminActionHandlerFactory.create(action);
		baseAction.perform(request, response);
		
	}

}