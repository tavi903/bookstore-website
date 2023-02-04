package com.tavi903.action.user;

import static com.tavi903.utils.ProjectUtils.*;

import java.io.IOException;
import java.sql.Timestamp;
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
public class EditUserAction implements BaseAction {
	
	private final UserService userService;
	
	@Inject
	public EditUserAction(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int pageSize = ApplicationConfig.USER_PAGE_SIZE;
		
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullName");
		String password = request.getParameter("password");
		User user = new User(email, password, fullName);
		user.setId(Long.parseLong(request.getParameter("userId")));
		user.setLastUpdate(Timestamp.valueOf(request.getParameter("lastUpdate")));

		userService.update(user, getUserLogged(request));

		long totalUsers = userService.count();
		List<User> listUsers = userService.findAll(1, pageSize);
		
		/* Add Request Attributes */

		request.setAttribute("message", "User updated.");
		request.setAttribute("currentPage", 1);
		request.setAttribute("listUsers", listUsers);
		request.setAttribute("totalPages",
				totalUsers % pageSize > 0 ? (totalUsers / pageSize + 1) : (totalUsers / pageSize));
		
		/* ********************** */
		
		loadJsp(request, response, "view/user_list.jsp");

	}

}	
