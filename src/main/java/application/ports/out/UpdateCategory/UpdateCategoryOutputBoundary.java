package application.ports.out.UpdateCategory;

import application.dtos.UpdateCategory.UpdateCategoryOutputData;

public interface UpdateCategoryOutputBoundary {
	void present(UpdateCategoryOutputData output);
}
