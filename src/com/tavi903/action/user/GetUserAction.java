package com.tavi903.action.user;

import static com.tavi903.utils.ProjectUtils.*;

import java.io.IOException;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tavi903.action.BaseAction;
import com.tavi903.entity.User;
import com.tavi903.service.UserService;

@Singleton
public class GetUserAction implements BaseAction {
	
	private final UserService userService;
	
	@Inject
	public GetUserAction(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Optional<User> user = userService.find(Long.parseLong(request.getParameter("id")));
		if (user.isPresent()) {
			request.setAttribute("user", user.get());
			loadJsp(request, response, "view/user_form.jsp");
		} else {
			loadMessageJsp(request, response, "User cannot be found.");
		}

	}

}
