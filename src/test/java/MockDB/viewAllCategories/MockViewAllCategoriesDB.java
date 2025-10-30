package MockDB.viewAllCategories;

import java.util.ArrayList;
import java.util.List;

import application.dtos.viewAllCategories.ViewAllCategoriesDTO;
import application.ports.out.viewAllCategories.ViewAllCategoriesDAO;

public class MockViewAllCategoriesDB implements ViewAllCategoriesDAO{
	private List<ViewAllCategoriesDTO> data = new ArrayList<>();
	
	public MockViewAllCategoriesDB() {
		data.add(new ViewAllCategoriesDTO(1, "Laptop"));
		data.add(new ViewAllCategoriesDTO(2, "Mouse"));
	}

	@Override
	public List<ViewAllCategoriesDTO> findAll() {
		return data;
	}
}
