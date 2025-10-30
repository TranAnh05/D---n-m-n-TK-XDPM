package application.ports.out.addNewCategory;

import application.dtos.addNewCategory.AddNewCategoryDTO;

public interface AddNewCategoryDAO {
	AddNewCategoryDTO findByName(String name);
	AddNewCategoryDTO save(AddNewCategoryDTO category);
}
