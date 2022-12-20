package action.category;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.BaseAction;
import config.ApplicationConfig;
import entity.Category;
import service.CategoryService;

import static utils.ProjectUtils.*;

@Singleton
public class CreateCategoryAction implements BaseAction {
	
	private final CategoryService categoryService;
	
	@Inject
	public CreateCategoryAction(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int pageSize = ApplicationConfig.CATEGORY_PAGE_SIZE;

		String name = request.getParameter("name");
		boolean isDeleted = Boolean.parseBoolean(request.getParameter("isDeleted"));
		Category category = new Category(name, isDeleted);

		categoryService.create(category);
		
		long totalCategories = (long) getFromCache(request, "totalCategories");
		List<Category> categories = categoryService.findAll(1, pageSize);
				
		/* Add Request Attributes */

		request.setAttribute("message", "The category has been created.");
		request.setAttribute("currentPage", 1);
		request.setAttribute("categories", categories);
		request.setAttribute("totalPages",
				totalCategories % pageSize > 0 ? (totalCategories / pageSize + 1) : (totalCategories / pageSize));
		
		/* ********************** */
		
		loadJsp(request, response, "view/category_list.jsp");

	}

}
