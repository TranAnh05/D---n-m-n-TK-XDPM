package application.ports.out.viewAllOrders;

import application.dtos.viewAllOrders.ViewAllOrdersOutputData;

public interface ViewAllOrdersOutputBoundary {
	void present(ViewAllOrdersOutputData outData);
}
