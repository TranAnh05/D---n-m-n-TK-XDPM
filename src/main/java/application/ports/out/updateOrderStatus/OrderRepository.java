package application.ports.out.updateOrderStatus;

import application.dtos.UpdateOrderStatus.OrderDataDB;

public interface OrderRepository {
	OrderDataDB findById(int id);
	OrderDataDB update(OrderDataDB order);
}
