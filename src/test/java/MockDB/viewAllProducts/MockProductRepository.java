package MockDB.viewAllProducts;

import java.util.ArrayList;
import java.util.List;
import application.dtos.viewAllProducts.ProductFromDBDTO;
import application.ports.out.viewAllProducts.ProductRepository;

public class MockProductRepository implements ProductRepository{
	List<ProductFromDBDTO> list = new ArrayList<ProductFromDBDTO>();
	
	public MockProductRepository() {
		ProductFromDBDTO dto = new ProductFromDBDTO();
		dto.id = 1;
		dto.name = "Dell XPS";
		dto.description = "Description";
		dto.price = 1000;
		dto.stockQuantity = 10;
		dto.imageUrl = "";
		dto.categoryId = 1;
		
		
		ProductFromDBDTO dto2 = new ProductFromDBDTO();
		dto2.id = 2;
		dto2.name = "Mouse Logitech";
		dto2.description = "Description 2";
		dto2.price = 500;
		dto2.stockQuantity = 5;
		dto2.imageUrl = "";
		dto2.categoryId = 2;
		
		list.add(dto);
		list.add(dto2);
	}
	
	@Override
	public List<ProductFromDBDTO> findAll() {
		return list;
	}

}
