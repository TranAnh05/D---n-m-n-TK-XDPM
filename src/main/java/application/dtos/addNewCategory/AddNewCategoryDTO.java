package application.dtos.addNewCategory;

public class AddNewCategoryDTO {
	public int id;
	public String name;
	
	public AddNewCategoryDTO() {
		
	}
	
	public AddNewCategoryDTO(int id, String name) {
		this.id = id;
		this.name = name;
	}
}
