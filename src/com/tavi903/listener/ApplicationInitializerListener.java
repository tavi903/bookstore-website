package com.tavi903.listener;

import java.io.FileInputStream;
import java.util.logging.LogManager;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.tavi903.cache.BookStoreWebsiteCache;
import com.tavi903.config.ApplicationConfig;
import com.tavi903.utils.LogDbHandler;

import static com.tavi903.config.ApplicationConfig.logger;

/**
 * It reads the logging.properties file, associate the cache with the ServletContext
 */
public class ApplicationInitializerListener implements ServletContextListener {
	
	@Inject
	private BookStoreWebsiteCache cache;
	
	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
	}
	
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		
		try {
			
			String path = getClass().getResource("./../../../../logging.properties").getPath();
			FileInputStream fileInputStream = new FileInputStream(path);
			LogManager.getLogManager().readConfiguration(fileInputStream);

			logger.setLevel(ApplicationConfig.DEFAULT_LEVEL);
			logger.addHandler(new LogDbHandler());
		
			servletContextEvent.getServletContext().setAttribute("cache", cache);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}