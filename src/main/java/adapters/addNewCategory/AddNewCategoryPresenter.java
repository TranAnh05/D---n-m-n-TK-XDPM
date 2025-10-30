package adapters.addNewCategory;

import application.dtos.addNewCategory.AddNewCategoryOutputData;
import application.ports.out.addNewCategory.AddNewCategoryOutputBoundary;

public class AddNewCategoryPresenter implements AddNewCategoryOutputBoundary{
	private AddNewCategoryViewModel model;
	
	public AddNewCategoryPresenter(AddNewCategoryViewModel model) {
		this.model = model;
	}
	
	public AddNewCategoryViewModel getModel() {
		return model;
	}
	
	@Override
	public void present(AddNewCategoryOutputData output) {
		model.message = output.message;
		model.success = output.success;
	}
}
