package application.ports.out.viewAllCategories;

import application.dtos.viewAllCategories.ViewAllCategoriesOutputData;

public interface ViewAllCategoriesOutputBoundary {
	void present(ViewAllCategoriesOutputData output);
}
