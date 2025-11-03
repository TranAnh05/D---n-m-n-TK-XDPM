package domain.entities;

import java.time.LocalDateTime;

public class Order {
	private int id;
    private int userId; // ID của Customer đặt hàng
    private LocalDateTime orderDate; // Ngày giờ đặt hàng
    private double totalAmount; // Tổng giá trị đơn hàng
    private OrderStatus status; // Trạng thái (dùng Enum)
    
    public Order(int id, int userId, LocalDateTime orderDate, double totalAmount, OrderStatus status) {
        this.id = id;
        this.userId = userId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
    }
    
    public int getId() { return id; }
    public int getUserId() { return userId; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public double getTotalAmount() { return totalAmount; }
    public OrderStatus getStatus() { return status; }
    
    // Setters (cho logic nghiệp vụ, vd: Update Status)
    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
