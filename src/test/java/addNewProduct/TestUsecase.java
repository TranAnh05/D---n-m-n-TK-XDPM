package addNewProduct;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import MockDB.addNewProduct.MockCategoryRepository;
import MockDB.addNewProduct.MockProductRepository;
import adapters.addNewProduct.AddNewProductPresenter;
import adapters.addNewProduct.AddNewProductViewModel;
import application.dtos.addNewProduct.AddNewProductDTO;
import application.dtos.addNewProduct.AddNewProductInputData;
import application.usecases.addNewProduct.AddNewProductUsecase;

public class TestUsecase {
	@Test
	public void test() {
		// Khoi tao du lieu 
		AddNewProductInputData data = new AddNewProductInputData();
		MockCategoryRepository listCategory = new MockCategoryRepository();
		data.name = "Dell XPS";
		data.description = "Chat luong cao, de dung";
		data.price = 1000;
		data.stockQuantity = 10;
		data.imageUrl = "";
		data.categoryId = 1;
		
		AddNewProductViewModel model = new AddNewProductViewModel();
		AddNewProductPresenter presenter = new AddNewProductPresenter(model);
		MockProductRepository daoProduct = new MockProductRepository();
		
		AddNewProductUsecase usecase = new AddNewProductUsecase(presenter, daoProduct , listCategory);
		usecase.execute(data);
		
		 assertEquals(true, usecase.getOutData().success);
		 
		 // Kiem tra csdl 
		 AddNewProductDTO savedData = daoProduct.findByName("Dell XPS");
		 assertNotNull(savedData);
		 assertEquals(1000, savedData.price);
		 
		 // Test presenter
		 assertEquals(true, presenter.getModel().success);
	}
}
