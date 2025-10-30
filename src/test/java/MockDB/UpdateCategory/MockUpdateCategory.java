package MockDB.UpdateCategory;

import java.util.ArrayList;
import java.util.List;

import application.dtos.UpdateCategory.UpdateCategoryDTO;
import application.ports.out.UpdateCategory.UpdateCategoryDAO;

public class MockUpdateCategory implements UpdateCategoryDAO{
	private List<UpdateCategoryDTO> list = new ArrayList<>();
	
	public MockUpdateCategory() {
		list.add(new UpdateCategoryDTO(1, "Laptop"));
		list.add(new UpdateCategoryDTO(2, "Mouse"));
	}

	@Override
	public UpdateCategoryDTO findByName(String newName) {
		for(UpdateCategoryDTO dto : list) {
			if(dto.name == newName) {
				return dto;
			}
		}
		return null;
	}

	@Override
	public UpdateCategoryDTO findById(int id) {
		for(UpdateCategoryDTO dto : list) {
			if(dto.id == id) {
				return dto;
			}
		}
		return null;
	}

	@Override
	public UpdateCategoryDTO update(UpdateCategoryDTO newCategory) {
		for(UpdateCategoryDTO existingItem : list) {
			if(existingItem.id == newCategory.id) {
				existingItem.name = newCategory.name;
				return existingItem;
			}
		}
		return null;
	}

}
