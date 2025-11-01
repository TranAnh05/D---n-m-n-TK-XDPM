package MockDB.deleteProduct;

import java.util.HashSet;
import java.util.Set;

import application.ports.out.deleteProduct.OrderRepository;

public class MockOrderRepository implements OrderRepository{
	// Dung set de luu id cac san pham dang co trong don hang
	private Set<Integer> productsInUse = new HashSet<>();
	
	public MockOrderRepository() {
		productsInUse.add(1);
		productsInUse.add(2);
	}
	
	@Override
	public boolean isProductInAnyOrder(int productId) {
		return productsInUse.contains(productId);
	}
}
