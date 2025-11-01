package application.ports.in.deleteProduct;

import application.dtos.deleteProduct.DeleteProductInputData;

public interface DeleteProductInputBoundary {
	void execute(DeleteProductInputData inputData);
}
