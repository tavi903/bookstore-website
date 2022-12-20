package factory.admin;

import javax.inject.Inject;
import javax.inject.Singleton;

import action.BaseAction;
import action.RedirectAction;
import config.ActionConfig;
import action.LoginAction;

import static utils.ProjectUtils.*;

@Singleton
public class BasicActionFactory {

	private final BaseAction homeAction;
	private final BaseAction logoutAction;
	private final LoginAction loginAction;
	private final RedirectAction redirectAction;
	
	@Inject
	public BasicActionFactory(
			LoginAction loginAction,
			RedirectAction redirectAction) {
		
		this.homeAction = (request, response) -> {	
			loadJsp(request, response, "view/index.jsp");
		};
		this.logoutAction = (request, response) -> {
			request.getSession().invalidate();
			loadJsp(request, response, "view/login.jsp");
		};
		this.loginAction = loginAction;
		this.redirectAction = redirectAction;
	
	}
	
	public BaseAction create(String action) {
		
		if(action.equals(ActionConfig.HOME)) {
			return homeAction;
		} else if (action.equals(ActionConfig.LOGOUT)) {
			return logoutAction;
		} else if (action.equals(ActionConfig.LOGIN)) {
			return loginAction;
		} else if(action.startsWith(ActionConfig.REDIRECT)) {
			return redirectAction;
		} else {
			throw new RuntimeException("Action " + action + " not present.");
		}
		
	}
	
}
