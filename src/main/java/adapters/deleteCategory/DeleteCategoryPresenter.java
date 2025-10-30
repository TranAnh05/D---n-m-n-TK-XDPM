package adapters.deleteCategory;

import application.dtos.deleteCategory.DeleteCategoryOutputData;
import application.ports.out.deleteCategory.DeleteCategoryOutputBoundary;

public class DeleteCategoryPresenter implements DeleteCategoryOutputBoundary {
	private DeleteCategoryViewModel model;
	
	public DeleteCategoryPresenter() {
		
	}
	
	public DeleteCategoryPresenter(DeleteCategoryViewModel model) {
		this.model = model;
	}
	
	public DeleteCategoryViewModel getModel() {
		return model;
	}

	@Override
	public void present(DeleteCategoryOutputData outData) {
		model.message = outData.message;
		model.success = outData.success;
	}
}
