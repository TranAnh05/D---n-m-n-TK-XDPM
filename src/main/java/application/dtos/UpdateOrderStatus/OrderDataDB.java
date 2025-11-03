package application.dtos.UpdateOrderStatus;

import java.time.LocalDateTime;

import domain.entities.OrderStatus;

public class OrderDataDB {
	public int id;
    public int userId;
    public LocalDateTime orderDate;
    public double totalAmount;
    public OrderStatus status;
}
