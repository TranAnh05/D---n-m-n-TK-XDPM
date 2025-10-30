package MockDB.addNewCategory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import application.dtos.addNewCategory.AddNewCategoryDTO;
import application.ports.out.addNewCategory.AddNewCategoryDAO;
import domain.entities.Category;

public class MockAddNewCategory implements AddNewCategoryDAO{
	// Dùng HashMap để giả lập Bảng (Table) trong CSDL
	private Map<Integer, AddNewCategoryDTO> database = new HashMap<>();
	private int sequence = 0; // Giả lập auto-increment ID
	
	@Override
	public AddNewCategoryDTO findByName(String name) {
//		Tim trong HashMap
		for(AddNewCategoryDTO c : database.values()) {
			if(c.name.equalsIgnoreCase(name)) {
				return c;
			}
		}
		return null;
	}

	@Override
	public AddNewCategoryDTO save(AddNewCategoryDTO category) {
		if(category.id == 0) {
			sequence++;
			AddNewCategoryDTO newCategory = new AddNewCategoryDTO(sequence, category.name);
			database.put(sequence, newCategory);
			return newCategory;
		}
		return null;
	}
}
