package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import config.ActionConfig;

import static utils.ProjectUtils.*;

@WebFilter(urlPatterns = "/admin/*", filterName = "authentication")
public class AdminLoginFilter implements Filter {

	public AdminLoginFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession(false);

		boolean loggedIn = session != null && session.getAttribute("userEmail") != null;
		String loginURI  = httpServletRequest.getContextPath() + "/admin/view?action="+ActionConfig.LOGIN;
		String reqURI    = httpServletRequest.getRequestURI()+"?"+httpServletRequest.getQueryString();
		
		if (loggedIn) {
			if(isRequestURIWeirdOrRandom(httpServletRequest)) {
				loadJsp(httpServletRequest, response, "view/index.jsp");
			} else {
				chain.doFilter(request, response);
			}
		} else {
			
			if(reqURI.equals(loginURI)) {
				chain.doFilter(request, response);
			} else {
				loadJsp(httpServletRequest, response, "view/login.jsp");
			}
			
		}

	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
	
	private boolean isRequestURIWeirdOrRandom(HttpServletRequest httpServletRequest) {
		return !(httpServletRequest.getRequestURI().equals(httpServletRequest.getContextPath() + "/admin/view")
				|| httpServletRequest.getRequestURI().equals(httpServletRequest.getContextPath() + "/admin/multipart"));
	}

}
