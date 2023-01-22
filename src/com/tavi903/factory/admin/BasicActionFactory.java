package com.tavi903.factory.admin;

import static com.tavi903.utils.ProjectUtils.*;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.tavi903.action.BaseAction;
import com.tavi903.action.LoginAction;
import com.tavi903.action.RedirectAction;
import com.tavi903.config.ActionConfig;

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
