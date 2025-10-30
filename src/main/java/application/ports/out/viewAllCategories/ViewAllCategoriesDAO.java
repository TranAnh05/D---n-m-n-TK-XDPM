package application.ports.out.viewAllCategories;

import java.util.List;

import application.dtos.viewAllCategories.ViewAllCategoriesDTO;

public interface ViewAllCategoriesDAO {
	List<ViewAllCategoriesDTO> findAll();
}
