package com.tavi903.cache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.tavi903.config.ApplicationConfig;
import com.tavi903.service.BookService;
import com.tavi903.service.CategoryService;

import static com.tavi903.config.ApplicationConfig.logger;

/*
 * Simple Cache
 */
@Singleton
public class BookStoreWebsiteCache {

    private final ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
    
    private final CategoryService categoryService; 
    private final BookService bookService;
    
    @Inject
    public BookStoreWebsiteCache(CategoryService categoryService, BookService bookService) {
    	this.bookService = bookService;
    	this.categoryService = categoryService;
        Thread t = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        refresh();
                        Thread.sleep(ApplicationConfig.MINUTES_REFRESH * 60000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        // setDaemon(): Marks this thread as either a daemon thread or a user thread.
        // The Java Virtual Machine exits when the only threads running are all daemon threads.
        // This method must be invoked before the thread is started.
        t.setDaemon(true);
        // start(): Causes this thread to begin execution; the Java Virtual Machine calls the run method of this thread.
        // The result is that two threads are running concurrently:
        // the current thread (which returns from the call to the start method) and the other thread (which executes its run method).
        t.start();
    }
    
    public Object get(String key) {
    	return map.get(key);
    }

    private void refresh() {
    	map.clear();
    	map.put("categories", categoryService.findAll());
    	map.put("totalCategories", categoryService.count());
    	map.put("titles", bookService.selectTitles());
    	map.put("authors", bookService.selectAuthors());
    	map.put("minPrice", bookService.minPrice());
    	map.put("maxPrice", bookService.maxPrice());
    	map.put("totalBooks", bookService.count());
    	logger.log(Level.INFO, "The cache has been refreshed!");
    }
}