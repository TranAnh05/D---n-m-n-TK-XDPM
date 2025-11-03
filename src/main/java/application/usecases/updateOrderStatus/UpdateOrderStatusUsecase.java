package application.usecases.updateOrderStatus;

import application.dtos.UpdateOrderStatus.OrderDTO;
import application.dtos.UpdateOrderStatus.OrderDataDB;
import application.dtos.UpdateOrderStatus.UpdateOrderStatusInputData;
import application.dtos.UpdateOrderStatus.UpdateOrderStatusOutputData;
import application.dtos.UpdateOrderStatus.UserDataDB;
import application.ports.in.updateOrderStatus.UpdateOrderStatusInputBoundary;
import application.ports.out.updateOrderStatus.OrderRepository;
import application.ports.out.updateOrderStatus.UpdateOrderStatusOuputBoundary;
import application.ports.out.updateOrderStatus.UserRepository;
import domain.entities.Order;
import domain.entities.OrderStatus;

public class UpdateOrderStatusUsecase implements UpdateOrderStatusInputBoundary{
	private UpdateOrderStatusOuputBoundary outBoundary;
	private OrderRepository daoOrder;
	private UserRepository daoUser;
	private UpdateOrderStatusOutputData outputData;

	public UpdateOrderStatusUsecase() {
		
	}
	
	public UpdateOrderStatusUsecase(UpdateOrderStatusOuputBoundary outBoundary, OrderRepository daoOrder,
			UserRepository daoUser) {
		this.outBoundary = outBoundary;
		this.daoOrder = daoOrder;
		this.daoUser = daoUser;
	}
	
	public UpdateOrderStatusOutputData getOutData() {
		return outputData;
	}

	@Override
	public void execute(UpdateOrderStatusInputData data) {
		outputData = new UpdateOrderStatusOutputData();
		
		try {
			// 1. Chuyển đổi String input -> Enum
			OrderStatus newStatus;
			newStatus = OrderStatus.valueOf(data.newStatus.toUpperCase());
			try {
				
			} catch (Exception e) {
				outputData.success = false;
                outputData.message = "Trạng thái được chọn không hợp lệ.";
                outBoundary.present(outputData);
                return;
			}
			
			// 2. Tìm OrderData
			OrderDataDB orderData = daoOrder.findById(data.orderId);
			if (orderData == null) {
                outputData.success = false;
                outputData.message = "Không tìm thấy đơn hàng để cập nhật.";
                outBoundary.present(outputData);
                return;
            }
			
			// 3. Chuyển sang Entity
			Order orderEntity = new Order(
	                orderData.id, orderData.userId, orderData.orderDate,
	                orderData.totalAmount, orderData.status
	            );
			orderEntity.setStatus(newStatus);
			
			// 4. Chuyen sang DTO
			OrderDataDB orderDAO = convertToDataDAO(orderEntity);
			
			// 5. Luu vao csdl
			OrderDataDB updatedOrder = daoOrder.update(orderDAO);
			
			// 6. Bao cao thanh cong
			outputData.success = true;
            outputData.message = "Cập nhật trạng thái thành công!";
            outputData.updatedOrder = mapToOutputData(updatedOrder);
            outBoundary.present(outputData);
		} catch (Exception e) {
			// 7. Xử lý Lỗi Hệ thống
            outputData.success = false;
            outputData.message = "Đã xảy ra lỗi hệ thống. Vui lòng thử lại.";
            outBoundary.present(outputData);
		}
		
	}

	private OrderDTO mapToOutputData(OrderDataDB updatedOrder) {
		OrderDTO dto = new OrderDTO();
		
		dto.id = updatedOrder.id;
		dto.totalAmount = updatedOrder.totalAmount;
		dto.status = updatedOrder.status;
		dto.orderDate = updatedOrder.orderDate;
		
		UserDataDB user = daoUser.findById(updatedOrder.userId);
		dto.userEmail = (user != null) ? user.email : "Người dùng đã bị xóa";
		
		return dto;
	}

	private OrderDataDB convertToDataDAO(Order orderEntity) {
		OrderDataDB dataDAO = new OrderDataDB();
		
		dataDAO.id = orderEntity.getId();
		dataDAO.userId = orderEntity.getUserId();
		dataDAO.orderDate = orderEntity.getOrderDate();
		dataDAO.totalAmount = orderEntity.getTotalAmount();
		dataDAO.status = orderEntity.getStatus();
		
		return dataDAO;
	}

}
