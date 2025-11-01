package deleteProduct;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import MockDB.deleteProduct.MockOrderRepository;
import MockDB.deleteProduct.MockProductRepository;
import adapters.deleteProduct.DeleteProductPresenter;
import adapters.deleteProduct.DeleteProductViewModel;
import application.dtos.deleteProduct.DeleteProductInputData;
import application.usecases.deleteProduct.DeleteProductUsecase;

public class TestUsecase {
	@Test
	public void test() {
		DeleteProductInputData data = new DeleteProductInputData();
		data.id = 3;
		DeleteProductViewModel model = new DeleteProductViewModel();
		DeleteProductPresenter presenter = new DeleteProductPresenter(model);
		MockProductRepository daoProduct = new MockProductRepository();
		MockOrderRepository daoOrder = new MockOrderRepository();
		
		DeleteProductUsecase usecase = new DeleteProductUsecase(presenter, daoProduct, daoOrder);
		
		usecase.execute(data);
		assertEquals(true, usecase.getOutData().success);
	}
}
