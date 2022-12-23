package listener;

import java.io.FileInputStream;
import java.util.logging.LogManager;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import cache.BookStoreWebsiteCache;

/**
 * It reads the logging.properties file, associate the cache with the ServletContext and initialize persistenceUnit
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
			
			String path = getClass().getResource("./../../logging.properties").getPath();
			FileInputStream fileInputStream = new FileInputStream(path);
			LogManager.getLogManager().readConfiguration(fileInputStream);
		
			servletContextEvent.getServletContext().setAttribute("cache", cache);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}