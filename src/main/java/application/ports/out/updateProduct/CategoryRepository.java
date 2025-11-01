package application.ports.out.updateProduct;

import application.dtos.updateProduct.CategoryDTO;

public interface CategoryRepository {
	CategoryDTO findById(int id);
}
