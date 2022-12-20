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
public class GetAllCategoriesAction implements BaseAction {
	
	private CategoryService categoryService;
	
	@Inject
	public GetAllCategoriesAction(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int pageSize = ApplicationConfig.CATEGORY_PAGE_SIZE;
		int page = Integer.parseInt(request.getParameter("page"));

		long totalCategories = (long) getFromCache(request, "totalCategories");
		List<Category> categories = categoryService.findAll(page, pageSize);
		
		/* Add Request Attributes */
		
		request.setAttribute("currentPage", page);
		request.setAttribute("categories", categories);
		request.setAttribute("totalPages",
				totalCategories % pageSize > 0 ? (totalCategories / pageSize + 1) : (totalCategories / pageSize));
			
		/* ********************** */
		
		loadJsp(request, response, "view/category_list.jsp");

	}

}
