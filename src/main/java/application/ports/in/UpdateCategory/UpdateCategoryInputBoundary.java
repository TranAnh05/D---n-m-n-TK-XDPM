package application.ports.in.UpdateCategory;

import application.dtos.UpdateCategory.UpdateCategoryInputData;

public interface UpdateCategoryInputBoundary {
	void execute(UpdateCategoryInputData data);
}
