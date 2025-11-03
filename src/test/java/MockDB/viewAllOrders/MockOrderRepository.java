package MockDB.viewAllOrders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import application.dtos.viewAllOrders.OrderDataDB;
import application.ports.out.viewAllOrders.OrderRepository;
import domain.entities.OrderStatus;

public class MockOrderRepository implements OrderRepository{
	private List<OrderDataDB> list = new ArrayList<OrderDataDB>();
	
	public MockOrderRepository() {
		OrderDataDB order1 = new OrderDataDB();
		order1.id = 1;
		order1.userId = 2;
		order1.orderDate = LocalDateTime.now();
		order1.totalAmount = 1500;
		order1.status = OrderStatus.PENDING;
		
		OrderDataDB order2 = new OrderDataDB();
		order2.id = 2;
		order2.userId = 2;
		order2.orderDate = LocalDateTime.now();
		order2.totalAmount = 1200;
		order2.status = OrderStatus.SHIPPED;
		
		list.add(order1);
		list.add(order2);
	}
	
	@Override
	public List<OrderDataDB> findAll() {
		return list;
	}

}