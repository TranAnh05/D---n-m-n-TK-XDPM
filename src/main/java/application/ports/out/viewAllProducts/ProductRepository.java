package application.ports.out.viewAllProducts;

import java.util.List;
import application.dtos.viewAllProducts.ProductFromDBDTO;

public interface ProductRepository {
	List<ProductFromDBDTO> findAll();
}
