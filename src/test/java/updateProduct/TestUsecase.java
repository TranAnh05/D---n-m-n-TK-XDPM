package updateProduct;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import MockDB.updateProduct.MockCategoryRepository;
import MockDB.updateProduct.MockProductRepository;
import adapters.updateProduct.UpdateProductPresenter;
import adapters.updateProduct.UpdateProductViewModel;
import application.dtos.updateProduct.UpdateProductInputData;
import application.ports.out.updateProduct.UpdateProductOutputBoundary;
import application.usecases.updateProduct.UpdateProductUsecase;

public class TestUsecase {
	@Test
	public void test() {
		UpdateProductInputData inputData = new UpdateProductInputData();
		inputData.id = 2;
		inputData.name = "Mouse Logitech Updated"; // Sua ten
		inputData.description = "Description";
		inputData.price = 1000;
		inputData.stockQuantity = 10;
		inputData.imageUrl = "";
		inputData.categoryId = 2;
		
		UpdateProductViewModel model = new UpdateProductViewModel();
		UpdateProductPresenter presenter = new UpdateProductPresenter(model);
		MockProductRepository daoProduct = new MockProductRepository();
		MockCategoryRepository daoCategory = new MockCategoryRepository();
		
		UpdateProductUsecase usecase = new UpdateProductUsecase(presenter, daoProduct, daoCategory);
		
		usecase.execute(inputData);
		assertEquals("Mouse Logitech Updated", usecase.getOutData().updatedProduct.name);
	}
}
