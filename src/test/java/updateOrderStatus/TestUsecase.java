package updateOrderStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import MockDB.updateOrderStatus.MockOrderRepository;
import MockDB.updateOrderStatus.MockUserRepository;
import adapters.updateOrderStatus.UpdateOrderStatusModel;
import adapters.updateOrderStatus.UpdateOrderStatusPresenter;
import application.dtos.UpdateOrderStatus.UpdateOrderStatusInputData;
import application.usecases.updateOrderStatus.UpdateOrderStatusUsecase;
import domain.entities.OrderStatus;

public class TestUsecase {
	@Test
	public void test() {
		UpdateOrderStatusInputData data = new UpdateOrderStatusInputData();
		data.orderId = 1;
		data.newStatus = "DELIVERED";
		
		UpdateOrderStatusModel model = new UpdateOrderStatusModel();
		UpdateOrderStatusPresenter presenter = new UpdateOrderStatusPresenter(model);
		MockOrderRepository daoOrder = new MockOrderRepository();
		MockUserRepository daoUser = new MockUserRepository();
		
		UpdateOrderStatusUsecase usecase = new UpdateOrderStatusUsecase(presenter, daoOrder, daoUser);
		usecase.execute(data);
		
		assertEquals(OrderStatus.DELIVERED, usecase.getOutData().updatedOrder.status);
		
		// test presenter
		assertEquals("DELIVERED", presenter.getModel().updatedOrder.status);
	}
}
