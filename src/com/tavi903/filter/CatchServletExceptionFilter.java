package com.tavi903.filter;

import static com.tavi903.utils.ProjectUtils.*;

import java.io.IOException;
import java.util.logging.Level;
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

import com.tavi903.utils.BookStoreException;

import static com.tavi903.config.ApplicationConfig.logger;


@WebFilter(urlPatterns = "/*", filterName = "catch-exception")
public class CatchServletExceptionFilter implements Filter {
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		try {
			chain.doFilter(request, response);
		} catch(BookStoreException e) {
			logger.log(Level.WARNING, e.getMessage(), e);
			loadMessageJsp(request, response, e.getMessage());
		} catch (ConstraintViolationException e) {
			logger.log(Level.WARNING, e.getMessage(), e);
			String message = e.getConstraintViolations().stream()
					.map(violation -> violation.getPropertyPath().toString() + ": " + violation.getMessage())
					.collect(Collectors.joining("<br />"));
			loadMessageJsp(request, response, message);
		} catch (OptimisticLockException e) {
			logger.log(Level.WARNING, e.getMessage(), e);
			loadMessageJsp(request, response, "This entity has been modified by another user!");
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.toString()+"\nStackTrace:\n"+ExceptionUtils.getStackTrace(e), e);
			loadMessageJsp(request, response, "Errore :(");
		}
		
	}
	
}
