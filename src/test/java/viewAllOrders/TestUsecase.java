package viewAllOrders;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import MockDB.viewAllOrders.MockOrderRepository;
import MockDB.viewAllOrders.MockUserRepository;
import adapters.viewAllOrders.ViewAllOrdersModel;
import adapters.viewAllOrders.ViewAllOrdersPresenter;
import application.usecases.viewAllOrders.ViewAllOrdersUsecase;

public class TestUsecase {
	@Test
	public void test() {
		ViewAllOrdersModel model = new ViewAllOrdersModel();
		ViewAllOrdersPresenter presenter = new ViewAllOrdersPresenter(model);
		MockOrderRepository daoOrder = new MockOrderRepository();
		MockUserRepository daoUser = new MockUserRepository();
		
		ViewAllOrdersUsecase usecase = new ViewAllOrdersUsecase(presenter, daoOrder, daoUser);
		usecase.execute();
		
		assertEquals(2, usecase.getOutData().orders.size());
		
		// test presenter 
		assertEquals("customer@gmail.com", presenter.getModel().orders.get(0).userEmail);
		
		
	}
}
