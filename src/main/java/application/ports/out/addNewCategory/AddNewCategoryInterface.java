package application.ports.out.addNewCategory;

import java.util.List;

import domain.entities.Category;

public interface AddNewCategoryInterface {
	Category findByName(String name);
	Category save(Category category);
	List<Category> findAll();
}
