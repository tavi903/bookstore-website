package action;

import java.io.IOException;

import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import config.ActionConfig;

import static utils.ProjectUtils.*;

@Singleton
public class RedirectAction implements BaseAction {

	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String targetJsp = request.getParameter("action").substring(ActionConfig.REDIRECT.length()+1);
		loadJsp(request, response, "view/"+targetJsp);
	}

}
