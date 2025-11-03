package application.ports.out.viewAllOrders;

import java.util.List;

import application.dtos.viewAllOrders.OrderDataDB;

public interface OrderRepository {
	List<OrderDataDB> findAll();
}
