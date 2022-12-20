package action;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;

import static utils.ProjectUtils.*;

@Singleton
public class LoginAction implements BaseAction {
	
	private final UserService userService;

	@Inject
	public LoginAction(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userEmail = request.getParameter("userEmail");
		String password  = request.getParameter("password");

		if (userService.canUserLogin(userEmail, password)) {
			request.getSession().setAttribute("userEmail", userEmail);
			loadJsp(request, response, "view/index.jsp");
		} else {
			request.setAttribute("message", "Login failed!");
			loadJsp(request, response, "view/login.jsp");
		}

	}

}
