package application.dtos.UpdateCategory;

public class UpdateCategoryDTO {
	public int id;
	public String name;
	
	public UpdateCategoryDTO() {
		
	}
	
	public UpdateCategoryDTO(int id, String name) {
		this.id = id;
		this.name = name;
	}
}
