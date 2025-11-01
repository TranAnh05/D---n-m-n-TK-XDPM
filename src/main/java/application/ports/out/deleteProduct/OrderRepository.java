package application.ports.out.deleteProduct;

public interface OrderRepository {
	boolean isProductInAnyOrder(int productId);
}
