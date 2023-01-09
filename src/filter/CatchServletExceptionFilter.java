package filter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.persistence.OptimisticLockException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.exception.ExceptionUtils;

import utils.BookStoreException;

import static utils.ProjectUtils.*;


@WebFilter(urlPatterns = "/*", filterName = "catch-exception")
public class CatchServletExceptionFilter implements Filter {
	
	private final Logger logger = Logger.getLogger(CatchServletExceptionFilter.class.getName());

	public CatchServletExceptionFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		try {
			chain.doFilter(request, response);
		} catch(BookStoreException e) {
			logger.log(Level.WARNING, e.toString());
			loadMessageJsp(request, response, e.getMessage());
		} catch (ConstraintViolationException e) {
			logger.log(Level.WARNING, e.toString());
			String message = e.getConstraintViolations().stream()
					.map(violation -> violation.getPropertyPath().toString() + ": " + violation.getMessage())
					.collect(Collectors.joining("<br />"));
			loadMessageJsp(request, response, message);
		} catch (OptimisticLockException e) {
			logger.log(Level.WARNING, e.toString());
			loadMessageJsp(request, response, "This entity has been modified by another user!");
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.toString()+"\nStackTrace:\n"+ExceptionUtils.getStackTrace(e));
			loadMessageJsp(request, response, "Errore :(");
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
