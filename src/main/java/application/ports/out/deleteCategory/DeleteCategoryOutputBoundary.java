package application.ports.out.deleteCategory;

import application.dtos.deleteCategory.DeleteCategoryOutputData;

public interface DeleteCategoryOutputBoundary {
	void present(DeleteCategoryOutputData outData);
}
