package application.ports.out.deleteCategory;

import application.dtos.deleteCategory.DeleteCategoryDTO;

public interface DeleteCategoryDAO {
	DeleteCategoryDTO findById(int id);
	boolean deleteById(int id);
}
