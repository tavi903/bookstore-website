package com.tavi903.action.user;

import static com.tavi903.utils.ProjectUtils.*;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tavi903.action.BaseAction;
import com.tavi903.config.ApplicationConfig;
import com.tavi903.entity.User;
import com.tavi903.service.UserService;

@Singleton
public class DeleteUserAction implements BaseAction {
	
	private final UserService userService;
	
	@Inject
	public DeleteUserAction(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int pageSize = ApplicationConfig.USER_PAGE_SIZE;
		long userId = Long.parseLong(request.getParameter("id"));

		userService.delete(userId);
		
		long totalUsers = userService.count();
		List<User> listUsers = userService.findAll(1, pageSize);
		
		/* Add Request Attributes */

		request.setAttribute("message", "The user has been deleted.");
		request.setAttribute("currentPage", 1);
		request.setAttribute("listUsers", listUsers);
		request.setAttribute("totalPages",
				totalUsers % pageSize > 0 ? (totalUsers / pageSize + 1) : (totalUsers / pageSize));
		
		/* ********************** */
		
		loadJsp(request, response, "view/user_list.jsp");

	}

}
