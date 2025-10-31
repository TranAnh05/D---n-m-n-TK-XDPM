package application.ports.out.addNewProduct;

import application.dtos.addNewProduct.AddNewProductOutputData;

public interface AddNewProductOutputBoundary {
	void present(AddNewProductOutputData outData);
}
