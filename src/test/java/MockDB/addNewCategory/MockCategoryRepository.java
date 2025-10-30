package MockDB.addNewCategory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import application.ports.out.addNewCategory.AddNewCategoryInterface;
import domain.entities.Category;

public class MockCategoryRepository implements AddNewCategoryInterface{
	// Dùng HashMap để giả lập Bảng (Table) trong CSDL
	private Map<Integer, Category> database = new HashMap<>();
	private int sequence = 0; // Giả lập auto-increment ID
	
	@Override
	public Category findByName(String name) {
//		Tim trong HashMap
		for(Category c : database.values()) {
			if(c.getName().equalsIgnoreCase(name)) {
				return c;
			}
		}
		return null;
	}

	@Override
	public Category save(Category category) {
		if(category.getId() == 0) {
			sequence++;
			Category newCategory = new Category(sequence, category.getName());
			database.put(sequence, newCategory);
			return newCategory;
		}
		return null;
	}

	@Override
	public List<Category> findAll() {
		return database.values().stream().collect(Collectors.toList());
	}
}
