package application.dtos.viewAllOrders;

import java.time.LocalDateTime;

import domain.entities.OrderStatus;

public class OrderDataDB {
	public int id;
    public int userId;
    public LocalDateTime orderDate;
    public double totalAmount;
    public OrderStatus status;
    
    public OrderDataDB() {
    	
    }
    
    public OrderDataDB(int id, int userId, LocalDateTime orderDate, double totalAmount, OrderStatus status) {
        this.id = id;
        this.userId = userId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
    }
}
