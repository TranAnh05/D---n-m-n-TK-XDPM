package adapters.updateOrderStatus;

import java.time.format.DateTimeFormatter;
import application.dtos.UpdateOrderStatus.OrderDTO;
import application.dtos.UpdateOrderStatus.UpdateOrderStatusOutputData;
import application.ports.out.updateOrderStatus.UpdateOrderStatusOuputBoundary;
import domain.entities.OrderStatus;

public class UpdateOrderStatusPresenter implements UpdateOrderStatusOuputBoundary{
	private UpdateOrderStatusModel model;
	
	public UpdateOrderStatusPresenter() {
		
	}
	
	public UpdateOrderStatusPresenter(UpdateOrderStatusModel model) {
		this.model = model;
	}
	
	public UpdateOrderStatusModel getModel() {
		return model;
	}

	@Override
	public void present(UpdateOrderStatusOutputData outData) {
		model.message = outData.message;
		model.success = outData.success;
		model.updatedOrder = convertToViewData(outData.updatedOrder);
	}

	private OrderViewDTO convertToViewData(OrderDTO updatedOrder) {
		OrderViewDTO viewDto = new OrderViewDTO();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
		
		viewDto.id = String.valueOf(updatedOrder.id);
		viewDto.userEmail = updatedOrder.userEmail;
		viewDto.orderDate = updatedOrder.orderDate.format(formatter);
		viewDto.totalAmount = String.valueOf(updatedOrder.totalAmount);
		viewDto.status = convertRoleToString(updatedOrder.status);
		
		return viewDto;
	}

	private String convertRoleToString(OrderStatus status) {
		if(status == null) {
			return "Unknown";
		}
		
		switch (status) {
		case PENDING:
			return "PENDING";
		case PROCESSING:
			return "PROCESSING";
		case SHIPPED:
			return "SHIPPED";
		case DELIVERED:
			return "DELIVERED";
		case CANCELLED:
			return "CANCELLED";
		default:
			return "Unknown";
		}
		
	}
}
