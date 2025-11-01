package MockDB.viewAllProducts;

import java.util.ArrayList;
import java.util.List;

import application.dtos.viewAllProducts.CategoryDTO;
import application.ports.out.viewAllProducts.CategoryRepository;

public class MockCategoryRepository implements CategoryRepository{
	private List<CategoryDTO> list = new ArrayList<CategoryDTO>();
	
	public MockCategoryRepository() {
		list.add(new CategoryDTO(1, "Laptop"));
		list.add(new CategoryDTO(2, "Mouse"));
	}
	
	@Override
	public List<CategoryDTO> findAll() {
		return list;
	}

}
