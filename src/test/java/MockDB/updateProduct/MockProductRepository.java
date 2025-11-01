package MockDB.updateProduct;

import java.util.ArrayList;
import java.util.List;

import application.dtos.updateProduct.ProductFromDBDTO;
import application.ports.out.updateProduct.ProductRepository;

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
	public ProductFromDBDTO findById(int id) {
		for(ProductFromDBDTO dto : list) {
			if(dto.id == id) {
				return dto;
			}
		}
		return null;
	}

	@Override
	public ProductFromDBDTO findByName(String name) {
		for(ProductFromDBDTO dto : list) {
			if(dto.name == name) {
				return dto;
			}
		}
		return null;
	}

	@Override
	public ProductFromDBDTO update(ProductFromDBDTO productData) {
		for(ProductFromDBDTO dto : list) {
			if(dto.id == productData.id) {
				dto = productData;
				list.add(dto);
				return dto;
			}
		}
		return null;
	}

}
