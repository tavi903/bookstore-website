package action.user;

import java.io.IOException;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.BaseAction;
import entity.User;
import service.UserService;

import static utils.ProjectUtils.*;

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
