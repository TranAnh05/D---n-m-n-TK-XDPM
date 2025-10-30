package application.ports.in.addNewCategory;

import application.dtos.addNewCategory.AddNewCategoryInputData;

public interface AddNewCategoryInputBoundary {
	void execute(AddNewCategoryInputData input);
}
