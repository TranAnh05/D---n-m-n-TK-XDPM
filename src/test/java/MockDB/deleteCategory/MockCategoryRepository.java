package MockDB.deleteCategory;

import java.util.ArrayList;
import java.util.List;

import application.dtos.deleteCategory.DeleteCategoryDTO;
import application.ports.out.deleteCategory.DeleteCategoryDAO;

public class MockCategoryRepository implements DeleteCategoryDAO {
	private List<DeleteCategoryDTO> list = new ArrayList<DeleteCategoryDTO>();
	
	public MockCategoryRepository() {
		list.add(new DeleteCategoryDTO(1, "Laptop"));
		list.add(new DeleteCategoryDTO(2, "Mouse"));
	}
	@Override
	public DeleteCategoryDTO findById(int id) {
		for(DeleteCategoryDTO dto : list) {
			if(dto.id == id) {
				return dto;
			}
		}
		return null;
	}

	@Override
	public boolean deleteById(int id) {
		for(DeleteCategoryDTO dto : list) {
			if(dto.id == id) {
				list.remove(dto);
				return true;
			}
		}
		return false;
	}

}
