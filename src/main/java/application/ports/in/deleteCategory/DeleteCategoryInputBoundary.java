package application.ports.in.deleteCategory;

import application.dtos.deleteCategory.DeleteCategoryInputData;

public interface DeleteCategoryInputBoundary {
	void execute(DeleteCategoryInputData data);
}
