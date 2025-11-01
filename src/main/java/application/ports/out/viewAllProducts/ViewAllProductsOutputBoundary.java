package application.ports.out.viewAllProducts;

import application.dtos.viewAllProducts.ViewAllProductsOutputData;

public interface ViewAllProductsOutputBoundary {
	void present(ViewAllProductsOutputData outData);
}
