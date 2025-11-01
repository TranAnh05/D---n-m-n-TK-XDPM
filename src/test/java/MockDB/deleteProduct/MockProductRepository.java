package MockDB.deleteProduct;

import java.util.ArrayList;
import java.util.List;

import application.dtos.deleteProduct.ProductFromDBDTO;
import application.ports.out.deleteProduct.ProductRepository;

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
		

		
		ProductFromDBDTO dto3 = new ProductFromDBDTO();
		dto3.id = 3;
		dto3.name = "Mouse Logitech 3";
		dto3.description = "Description 3";
		dto3.price = 500;
		dto3.stockQuantity = 5;
		dto3.imageUrl = "";
		dto3.categoryId = 2;
		
		list.add(dto);
		list.add(dto2);
		list.add(dto3);
	}
	
	@Override
	public void deleteById(int id) {
		for(ProductFromDBDTO dto : list) {
			if(dto.id == id) {
				list.remove(dto);
				return;
			}
		}
	}

	@Override
	public ProductFromDBDTO findById(int id) {
		for(ProductFromDBDTO dto : list) {
			if(dto.id == id) {
				return dto;
			}
		}
		return null;
	}
}
