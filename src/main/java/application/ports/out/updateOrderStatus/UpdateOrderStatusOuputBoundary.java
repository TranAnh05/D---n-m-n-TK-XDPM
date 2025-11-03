package application.ports.out.updateOrderStatus;

import application.dtos.UpdateOrderStatus.UpdateOrderStatusOutputData;

public interface UpdateOrderStatusOuputBoundary {
	void present(UpdateOrderStatusOutputData outData);
}
