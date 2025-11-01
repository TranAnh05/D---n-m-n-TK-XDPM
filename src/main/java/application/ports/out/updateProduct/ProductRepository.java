package application.ports.out.updateProduct;

import application.dtos.updateProduct.ProductFromDBDTO;

public interface ProductRepository {
	ProductFromDBDTO findById(int id);
	ProductFromDBDTO findByName(String name);
	ProductFromDBDTO update(ProductFromDBDTO productData);
}
