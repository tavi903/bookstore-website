package com.tavi903.action.category;

import static com.tavi903.utils.ProjectUtils.*;

import java.io.IOException;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tavi903.action.BaseAction;
import com.tavi903.entity.Category;
import com.tavi903.service.CategoryService;

@Singleton
public class GetCategoryAction implements BaseAction {
	
	private final CategoryService categoryService;
	
	@Inject
	public GetCategoryAction(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Optional<Category> categoryMaybe = categoryService.find(Long.parseLong(request.getParameter("id")));
		
		if (categoryMaybe.isPresent()) {
			request.setAttribute("category", categoryMaybe.get());
			loadJsp(request, response, "view/category_form.jsp");
		} else {
			loadMessageJsp(request, response, "Category cannot be found.");
		}

	}

}
