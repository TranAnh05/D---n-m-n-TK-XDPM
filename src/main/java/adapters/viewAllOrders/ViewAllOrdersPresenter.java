package adapters.viewAllOrders;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import application.dtos.viewAllOrders.OrderDTO;
import application.dtos.viewAllOrders.ViewAllOrdersOutputData;
import application.ports.out.viewAllOrders.ViewAllOrdersOutputBoundary;
import domain.entities.OrderStatus;

public class ViewAllOrdersPresenter implements ViewAllOrdersOutputBoundary{
	private ViewAllOrdersModel model;
	
	public ViewAllOrdersPresenter() {
		
	}
	
	public ViewAllOrdersPresenter(ViewAllOrdersModel model) {
		this.model = model;
	}
	
	public ViewAllOrdersModel getModel() {
		return model;
	}
	
	
	
	@Override
	public void present(ViewAllOrdersOutputData outData) {
		model.success = outData.success;
		model.message = outData.message;
		model.orders = convertToViewData(outData.orders);
	}

	private List<OrderViewDTO> convertToViewData(List<OrderDTO> orders) {
		List<OrderViewDTO> viewDtos = new ArrayList<OrderViewDTO>();
		
		for(OrderDTO dto : orders) {
			OrderViewDTO viewDto = new OrderViewDTO();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
			
			viewDto.id = String.valueOf(dto.id);
			viewDto.userEmail = dto.userEmail;
			viewDto.orderDate = dto.orderDate.format(formatter);
			viewDto.totalAmount = String.valueOf(dto.totalAmount);
			viewDto.status = convertRoleToString(dto.status);
			
			viewDtos.add(viewDto);
		}
		
		return viewDtos;
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

