package application.ports.out.addNewProduct;

import application.dtos.addNewProduct.AddNewProductDTO;

public interface ProductRepository {
	AddNewProductDTO findByName(String name);
	AddNewProductDTO save(AddNewProductDTO product);
}
