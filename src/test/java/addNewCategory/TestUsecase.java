package addNewCategory;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import MockDB.addNewCategory.MockAddNewCategory;
import adapters.addNewCategory.AddNewCategoryPresenter;
import adapters.addNewCategory.AddNewCategoryViewModel;
import application.dtos.addNewCategory.AddNewCategoryInputData;
import application.ports.out.addNewCategory.AddNewCategoryOutputBoundary;
import application.usecases.addNewCategory.AddNewCategoryUsecase;

public class TestUsecase {
	@Test
	public void testFailedState() {
//		Test truong hop khong thanh cong
		AddNewCategoryInputData inputData = new AddNewCategoryInputData();
		inputData.categoryName = "";
		AddNewCategoryViewModel model = new AddNewCategoryViewModel();
		AddNewCategoryOutputBoundary outBoundary = new AddNewCategoryPresenter(model);
		
		AddNewCategoryUsecase usecase = new AddNewCategoryUsecase(outBoundary, null);
		
		usecase.execute(inputData);
		assertFalse(usecase.getOutdata().success);
	}
	
	@Test
	public void testSuccessfulState() {
		AddNewCategoryInputData inputData = new AddNewCategoryInputData("Laptop");
		
		AddNewCategoryViewModel model = new AddNewCategoryViewModel();
		AddNewCategoryPresenter presenter = new AddNewCategoryPresenter(model);
		MockAddNewCategory mockDB = new MockAddNewCategory();
		
		AddNewCategoryUsecase usecase = new AddNewCategoryUsecase(presenter, mockDB);
		
		usecase.execute(inputData);
		assertNotNull(mockDB.findByName("Laptop"));
	}
}
