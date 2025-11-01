package application.usecases.deleteProduct;

import application.dtos.deleteProduct.DeleteProductInputData;
import application.dtos.deleteProduct.DeleteProductOutputData;
import application.dtos.deleteProduct.ProductFromDBDTO;
import application.ports.in.deleteProduct.DeleteProductInputBoundary;
import application.ports.out.deleteProduct.DeleteProductOutputBoundary;
import application.ports.out.deleteProduct.OrderRepository;
import application.ports.out.deleteProduct.ProductRepository;
import domain.entities.Product;

public class DeleteProductUsecase implements DeleteProductInputBoundary{
	private DeleteProductOutputBoundary outBoundary;
	private ProductRepository daoProduct;
	private OrderRepository daoOrder;
	private DeleteProductOutputData outputData;
	
	
	public DeleteProductUsecase() {
		
	}
	
	public DeleteProductUsecase(DeleteProductOutputBoundary outBoundary, ProductRepository daoProduct,
			OrderRepository daoOrder) {
		this.outBoundary = outBoundary;
		this.daoProduct = daoProduct;
		this.daoOrder = daoOrder;
	}
	
	public DeleteProductOutputData getOutData() {
		return outputData;
	}



	@Override
	public void execute(DeleteProductInputData inputData) {
		outputData = new DeleteProductOutputData();
		
		try {
			// 1. Kiểm tra sản phẩm có tồn tại không
			ProductFromDBDTO existingProduct = daoProduct.findById(inputData.id);
			if (existingProduct == null) {
                outputData.success = false;
                outputData.message = "Không tìm thấy sản phẩm để xóa.";
                outBoundary.present(outputData);
                return;
            }
			
			// 2. Kiểm tra product co trong order nao khong
			if (daoOrder.isProductInAnyOrder(inputData.id)) {
                outputData.success = false;
                outputData.message = "Không thể xóa. Sản phẩm này đang nằm trong một hoặc nhiều đơn hàng.";
                outBoundary.present(outputData);
                return;
            }
			
			// 3. Xoa
			daoProduct.deleteById(inputData.id);
			
			// 4. Thong bao thanh cong;
			outputData.success = true;
			Product productEntity = convertToEntity(existingProduct);
            outputData.message = "Đã xóa thành công sản phẩm: " + productEntity.getName();
            outBoundary.present(outputData);
			
		} catch (Exception e) {
			// 5. Xử lý Lỗi Hệ thống
            outputData.success = false;
            outputData.message = "Đã xảy ra lỗi hệ thống. Vui lòng thử lại.";
            outBoundary.present(outputData);
		}
	}

	private Product convertToEntity(ProductFromDBDTO existingProduct) {
		Product product = new Product(
				existingProduct.id,
				existingProduct.name,
				existingProduct.description,
				existingProduct.price,
				existingProduct.stockQuantity,
				existingProduct.imageUrl,
				existingProduct.categoryId);
		
		return product;
	}

}
