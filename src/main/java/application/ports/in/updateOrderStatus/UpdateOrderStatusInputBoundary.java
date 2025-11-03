package application.ports.in.updateOrderStatus;

import application.dtos.UpdateOrderStatus.UpdateOrderStatusInputData;

public interface UpdateOrderStatusInputBoundary {
	void execute(UpdateOrderStatusInputData data);
}
