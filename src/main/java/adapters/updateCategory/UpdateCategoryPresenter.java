package adapters.updateCategory;

import application.dtos.UpdateCategory.UpdateCategoryDTO;
import application.dtos.UpdateCategory.UpdateCategoryOutputData;
import application.ports.out.UpdateCategory.UpdateCategoryOutputBoundary;

public class UpdateCategoryPresenter  implements UpdateCategoryOutputBoundary{
	private UpdateCategoryViewModel model;
	
	public UpdateCategoryPresenter() {
		
	}
	
	public UpdateCategoryPresenter(UpdateCategoryViewModel model) {
		this.model = model;
	}
	
	public UpdateCategoryViewModel getModel() {
		return model;
	}
	
	@Override
	public void present(UpdateCategoryOutputData output) {
		UpdateCategoryViewDTO viewDTO = convertToViewDTO(output.updatedCategory);
		model.message = output.message;
		model.success = output.success;
		model.updatedCategory = viewDTO;
	}

	private UpdateCategoryViewDTO convertToViewDTO(UpdateCategoryDTO updatedCategory) {
		UpdateCategoryViewDTO dto = new UpdateCategoryViewDTO(
				String.valueOf(updatedCategory.id),
				updatedCategory.name
		);
		return dto;
	}
}
