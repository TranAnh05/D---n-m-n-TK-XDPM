package addNewCategory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import adapters.addNewCategory.AddNewCategoryPresenter;
import adapters.addNewCategory.AddNewCategoryViewModel;
import application.dtos.addNewCategory.AddNewCategoryInputData;
import application.usecases.addNewCategory.AddNewCategoryUsecase;

public class TestPresenter {
	@Test
	public void test() {
		AddNewCategoryInputData inputData = new AddNewCategoryInputData("");
		AddNewCategoryViewModel model = new AddNewCategoryViewModel();
		AddNewCategoryPresenter presenter = new AddNewCategoryPresenter(model);
		
		AddNewCategoryUsecase usecase = new AddNewCategoryUsecase(presenter, null);
		
		usecase.execute(inputData);
		
		assertEquals(false, presenter.getModel().success);
	}
}
