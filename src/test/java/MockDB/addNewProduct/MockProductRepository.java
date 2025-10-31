package MockDB.addNewProduct;

import java.util.HashMap;
import java.util.Map;
import application.dtos.addNewProduct.AddNewProductDTO;
import application.ports.out.addNewProduct.ProductRepository;

public class MockProductRepository implements ProductRepository{
	private Map<Integer, AddNewProductDTO> database = new HashMap<>();
	private int sequence = 0; // Giả lập auto-increment ID

	@Override
	public AddNewProductDTO findByName(String name) {
		for(AddNewProductDTO product : database.values()) {
			if(product.name.equalsIgnoreCase(name)) {
				return product;
			}
		}
		return null;
	}

	@Override
	public AddNewProductDTO save(AddNewProductDTO product) {
		if(product.id == 0) {
			sequence++;
			AddNewProductDTO newProduct = new AddNewProductDTO();
			newProduct.id = sequence;
			newProduct.name = product.name;
			newProduct.description = product.description;	
			newProduct.price = product.price;
			newProduct.stockQuantity = product.stockQuantity;
			newProduct.imageUrl = product.imageUrl;
			newProduct.categoryId = product.categoryId;
			database.put(sequence, newProduct);
			return newProduct;
		}
		return null;
	}

}
