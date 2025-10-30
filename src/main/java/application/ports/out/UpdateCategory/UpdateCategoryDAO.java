package application.ports.out.UpdateCategory;

import application.dtos.UpdateCategory.UpdateCategoryDTO;

public interface UpdateCategoryDAO {
	UpdateCategoryDTO findByName(String newName);
	UpdateCategoryDTO findById(int id);
	UpdateCategoryDTO update(UpdateCategoryDTO newCategory);
}
