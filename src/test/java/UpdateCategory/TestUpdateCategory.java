package UpdateCategory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import MockDB.UpdateCategory.MockUpdateCategory;
import adapters.updateCategory.UpdateCategoryPresenter;
import adapters.updateCategory.UpdateCategoryViewModel;
import application.dtos.UpdateCategory.UpdateCategoryDTO;
import application.dtos.UpdateCategory.UpdateCategoryInputData;
import application.dtos.UpdateCategory.UpdateCategoryOutputData;
import application.usecases.updateCategory.UpdateCategoryUsecase;

public class TestUpdateCategory {
	@Test
	public void test() {
		UpdateCategoryInputData inputData = new UpdateCategoryInputData(1, "Laptop 8G");
		
		MockUpdateCategory dao = new MockUpdateCategory();
		UpdateCategoryViewModel model = new UpdateCategoryViewModel();
		UpdateCategoryPresenter presenter = new UpdateCategoryPresenter(model);
		
		
		UpdateCategoryUsecase usecase = new UpdateCategoryUsecase(dao, presenter);
		usecase.execute(inputData);
		
		UpdateCategoryOutputData output = usecase.getOutData();
		UpdateCategoryDTO outputDTO = output.updatedCategory;
		
		assertEquals("Laptop 8G", outputDTO.name);
		
		// Test presenter
		UpdateCategoryViewModel testModel = presenter.getModel();
		
		assertEquals("1", testModel.updatedCategory.id);
		assertEquals("Laptop 8G", testModel.updatedCategory.name);
		
	}
}
 