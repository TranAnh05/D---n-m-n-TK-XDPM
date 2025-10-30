package MockDB.deleteCategory;

import java.util.ArrayList;
import java.util.List;

import application.dtos.deleteCategory.DeleteCategoryDTO;
import application.ports.out.deleteCategory.ProductRepository;

public class MockProductRepository implements ProductRepository{
	private List<ProductDTO> list = new ArrayList<>();
	
	public MockProductRepository() {
		DeleteCategoryDTO category1 = new DeleteCategoryDTO(1, "Laptop");
		DeleteCategoryDTO category2 = new DeleteCategoryDTO(2, "Mouse");
		
		
		ProductDTO product1 = new ProductDTO();
		product1.id = 1;
		product1.name = "Laptop Dell";
		product1.description = "Hiệu năng cao, bền";
		product1.price = 1000;
		product1.stockQuantity = 10;
		product1.imageUrl = "";
		product1.category = category1;
		
		ProductDTO product2 = new ProductDTO();
		product2.id = 2;
		product2.name = "Mouse Logi";
		product2.description = "Hiệu năng cao, bền";
		product2.price = 500;
		product2.stockQuantity = 5;
		product2.imageUrl = "";
		product2.category = category2;
		
		list.add(product1);
		list.add(product2);
	}

	@Override
	public int countByCategoryById(int categoryId) {
		int count = 0;
		for(ProductDTO dto : list) {
			if(dto.category.id == categoryId) {
				count++;
			}
		}
		return count;
	}
	
}
