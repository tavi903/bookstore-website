package com.tavi903.action;

import static com.tavi903.utils.ProjectUtils.*;

import java.io.IOException;

import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tavi903.config.ActionConfig;

@Singleton
public class RedirectAction implements BaseAction {

	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String targetJsp = request.getParameter("action").substring(ActionConfig.REDIRECT.length()+1);
		loadJsp(request, response, "view/"+targetJsp);
	}

}
