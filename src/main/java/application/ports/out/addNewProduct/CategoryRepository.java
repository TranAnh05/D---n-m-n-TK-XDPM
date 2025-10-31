package application.ports.out.addNewProduct;

import application.dtos.addNewProduct.CategoryDTO;

public interface CategoryRepository {
	CategoryDTO findById(int id);
}
