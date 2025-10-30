package application.dtos.UpdateCategory;

public class UpdateCategoryInputData {
	public int id;
	public String newName;
	
	public UpdateCategoryInputData() {
		
	}
	
	public UpdateCategoryInputData(int id, String newName) {
		this.id = id;
		this.newName = newName;
	}
}
