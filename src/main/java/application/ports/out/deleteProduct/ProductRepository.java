package application.ports.out.deleteProduct;

import application.dtos.deleteProduct.ProductFromDBDTO;

public interface ProductRepository {
	void deleteById(int id);
	ProductFromDBDTO findById(int id);
}
