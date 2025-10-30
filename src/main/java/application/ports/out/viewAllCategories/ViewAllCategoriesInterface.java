package application.ports.out.viewAllCategories;

import java.util.List;

import application.dtos.viewAllCategories.ViewAllCategoriesDTO;

public interface ViewAllCategoriesInterface {
	List<ViewAllCategoriesDTO> findAll();
}
