package application.ports.out.addNewCategory;

import application.dtos.addNewCategory.AddNewCategoryOutputData;

public interface AddNewCategoryOutputBoundary {
	void present(AddNewCategoryOutputData output);
}
