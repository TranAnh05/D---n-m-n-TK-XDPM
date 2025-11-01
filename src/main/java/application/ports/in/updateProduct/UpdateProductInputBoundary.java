package application.ports.in.updateProduct;

import application.dtos.updateProduct.UpdateProductInputData;

public interface UpdateProductInputBoundary {
	void execute(UpdateProductInputData data);
}
