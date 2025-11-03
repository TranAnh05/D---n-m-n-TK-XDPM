package application.dtos.viewAllOrders;

import java.time.LocalDateTime;

import domain.entities.OrderStatus;

public class OrderDTO {
	public int id;
    public String userEmail; 
    public LocalDateTime orderDate; 
    public double totalAmount;
    public OrderStatus status;
}
