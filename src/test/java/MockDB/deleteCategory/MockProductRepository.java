package MockDB.deleteCategory;

import java.util.ArrayList;
import java.util.List;
import application.ports.out.deleteCategory.ProductRepository;

public class MockProductRepository implements ProductRepository{
	private List<ProductDTO> list = new ArrayList<>();
	
	public MockProductRepository() {
		
		
		ProductDTO product1 = new ProductDTO();
		product1.id = 1;
		product1.name = "Laptop Dell";
		product1.description = "Hiệu năng cao, bền";
		product1.price = 1000;
		product1.stockQuantity = 10;
		product1.imageUrl = "";
		product1.categoryId = 1;
		
		list.add(product1);
	}

	@Override
	public int countByCategoryById(int categoryId) {
		int count = 0;
		for(ProductDTO dto : list) {
			if(dto.categoryId == categoryId) {
				count++;
			}
		}
		return count;
	}
	
}
