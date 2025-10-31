package application.ports.in.addNewProduct;

import application.dtos.addNewProduct.AddNewProductInputData;

public interface AddNewProductInputBoundary {
	void execute(AddNewProductInputData data);
}
