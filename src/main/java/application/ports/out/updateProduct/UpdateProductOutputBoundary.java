package application.ports.out.updateProduct;

import application.dtos.updateProduct.UpdateProductOutputData;

public interface UpdateProductOutputBoundary {
	void present(UpdateProductOutputData outData);
}
