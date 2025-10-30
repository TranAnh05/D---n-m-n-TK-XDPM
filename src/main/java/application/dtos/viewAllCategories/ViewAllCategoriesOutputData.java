package application.dtos.viewAllCategories;

import java.util.List;

import domain.entities.Category;

public class ViewAllCategoriesOutputData {
	public List<ViewAllCategoriesDTO> categories;
    public boolean success;
    public String message;
}
