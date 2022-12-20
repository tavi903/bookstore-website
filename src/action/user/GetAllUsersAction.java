package action.user;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.BaseAction;
import config.ApplicationConfig;
import entity.User;
import service.UserService;

import static utils.ProjectUtils.*;

@Singleton
public class GetAllUsersAction implements BaseAction {
	
	private final UserService userService;
	
	@Inject
	public GetAllUsersAction(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int pageSize = ApplicationConfig.USER_PAGE_SIZE;
		int page = Integer.parseInt(request.getParameter("page"));

		long totalUsers = userService.count();
		List<User> listUsers = userService.findAll(page, pageSize);
		
		/* Add Request Attributes */

		request.setAttribute("currentPage", page);
		request.setAttribute("listUsers", listUsers);
		request.setAttribute("totalPages",
				totalUsers % pageSize > 0 ? (totalUsers / pageSize + 1) : (totalUsers / pageSize));
		
		/* ********************** */
		
		loadJsp(request, response, "view/user_list.jsp");

	}

}
