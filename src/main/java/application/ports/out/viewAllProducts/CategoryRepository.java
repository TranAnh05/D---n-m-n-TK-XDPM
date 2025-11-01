package application.ports.out.viewAllProducts;

import java.util.List;

import application.dtos.viewAllProducts.CategoryDTO;

public interface CategoryRepository {
	List<CategoryDTO> findAll();
}
