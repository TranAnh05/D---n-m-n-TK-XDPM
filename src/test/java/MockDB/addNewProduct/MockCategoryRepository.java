package MockDB.addNewProduct;

import java.util.ArrayList;
import java.util.List;

import application.dtos.addNewProduct.CategoryDTO;
import application.ports.out.addNewProduct.CategoryRepository;

public class MockCategoryRepository implements CategoryRepository{
	private List<CategoryDTO> list = new ArrayList<CategoryDTO>();
	
	public MockCategoryRepository() {
		list.add(new CategoryDTO(1, "Laptop"));
		list.add(new CategoryDTO(2, "Mouse"));
	}
	
	
	@Override
	public CategoryDTO findById(int id) {
		for(CategoryDTO c : list) {
			if(c.id == id) {
				return c;
			}
		}
		return null;
	}

}
