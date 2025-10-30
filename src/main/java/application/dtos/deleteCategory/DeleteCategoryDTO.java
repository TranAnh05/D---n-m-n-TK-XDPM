package application.dtos.deleteCategory;

public class DeleteCategoryDTO {
	public int id;
	public String name;
	
	public DeleteCategoryDTO() {
		
	}
	
	public DeleteCategoryDTO(int id, String name) {
		this.id = id;
		this.name = name;
	}
}
