package application.usecases.viewAllOrders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import application.dtos.viewAllOrders.OrderDTO;
import application.dtos.viewAllOrders.OrderDataDB;
import application.dtos.viewAllOrders.UserDataDB;
import application.dtos.viewAllOrders.ViewAllOrdersOutputData;
import application.ports.in.viewAllOrders.ViewAllOrdersInputBoundary;
import application.ports.out.viewAllOrders.OrderRepository;
import application.ports.out.viewAllOrders.UserRepository;
import application.ports.out.viewAllOrders.ViewAllOrdersOutputBoundary;
import domain.entities.Order;

public class ViewAllOrdersUsecase implements ViewAllOrdersInputBoundary{
	private ViewAllOrdersOutputBoundary outBoundary;
	private OrderRepository daoOrder;
	private UserRepository daoUser;
	private ViewAllOrdersOutputData outputData;
	
	public ViewAllOrdersUsecase() {
		
	}
	
	
	public ViewAllOrdersUsecase(ViewAllOrdersOutputBoundary outBoundary, OrderRepository daoOrder, UserRepository daoUser) {
		this.outBoundary = outBoundary;
		this.daoOrder = daoOrder;
		this.daoUser = daoUser;
	}
	
	public ViewAllOrdersOutputData getOutData() {
		return outputData;
	}

	@Override
	public void execute() {
		outputData = new ViewAllOrdersOutputData();
		
		try {
			// 1. Lấy OrderData
			List<OrderDataDB> orderDataList = daoOrder.findAll();
			if (orderDataList.isEmpty()) {
                outputData.success = true;
                outputData.message = "Chưa có đơn hàng nào.";
                outputData.orders = new ArrayList<>();
                outBoundary.present(outputData);
                return;
            }
			
			// 2. Lấy UserData
			Map<Integer, UserDataDB> userMap = mapUsers(daoUser.findAll());
			
			// 3. Chuyen DTO sang Entity
			List<Order> orderEntities = mapDataToEntities(orderDataList);
			
			// 4. Chuyen entity -> output DTO (Lam giau du lieu)
			List<OrderDTO> safeOutputList = mapEntitiesToOutputData(orderEntities, userMap);
			
			// 5. Bao cao thanh cong
			outputData.success = true;
			outputData.message = "Lấy danh sách đơn hàng thành công.";
            outputData.orders = safeOutputList;
            outBoundary.present(outputData);
            
		} catch (Exception e) {
			// 6. Xử lý Lỗi Hệ thống
            outputData.success = false;
            outputData.message = "Đã xảy ra lỗi hệ thống khi tải đơn hàng.";
            outputData.orders = new ArrayList<>();
            outBoundary.present(outputData);
		}
	}


	private List<OrderDTO> mapEntitiesToOutputData(List<Order> orderEntities, Map<Integer, UserDataDB> userMap) {
		List<OrderDTO> outputList = new ArrayList<>();
		
		for(Order entity : orderEntities) {
			OrderDTO dto = new OrderDTO();
			
			dto.id = entity.getId();
			dto.totalAmount = entity.getTotalAmount();
			dto.orderDate = entity.getOrderDate();
			dto.status = entity.getStatus();
			
			UserDataDB user = userMap.get(entity.getUserId());
			if (user != null) {
                dto.userEmail = user.email;
            } else {
                dto.userEmail = "Người dùng đã bị xóa"; // Logic phòng thủ
            }
            
            outputList.add(dto);
		}
		
		return outputList;
	}


	private List<Order> mapDataToEntities(List<OrderDataDB> orderDataList) {
		List<Order> orderEntities = new ArrayList<>();
        for (OrderDataDB data : orderDataList) {
            orderEntities.add(new Order(
                data.id, data.userId, data.orderDate, data.totalAmount, data.status
            ));
        }
        return orderEntities;
	}


	private Map<Integer, UserDataDB> mapUsers(List<UserDataDB> userDataList) {
		Map<Integer, UserDataDB> userMap = new HashMap<>();
        for (UserDataDB data : userDataList) {
            userMap.put(data.id, data);
        }
        return userMap;
	}

}
