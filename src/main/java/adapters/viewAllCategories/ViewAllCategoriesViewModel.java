package adapters.viewAllCategories;

import java.util.List;

public class ViewAllCategoriesViewModel {
	public List<ViewAllCategoriesViewDTO> categories;
	public String message;
    public boolean success;
    
    public List<ViewAllCategoriesViewDTO> getCategories() { return categories; }
    public String getMessage() { return message; }
    public boolean isSuccess() { return success; }
}
