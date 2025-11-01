package application.ports.out.deleteProduct;

import application.dtos.deleteProduct.DeleteProductOutputData;

public interface DeleteProductOutputBoundary {
	void present(DeleteProductOutputData outData);
}
