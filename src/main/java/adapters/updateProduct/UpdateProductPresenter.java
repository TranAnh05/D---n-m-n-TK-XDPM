package adapters.updateProduct;

import application.dtos.updateProduct.ProductDTO;
import application.dtos.updateProduct.UpdateProductOutputData;
import application.ports.out.updateProduct.UpdateProductOutputBoundary;

public class UpdateProductPresenter implements UpdateProductOutputBoundary {
	private UpdateProductViewModel model;
	
	public UpdateProductPresenter() {
		
	}
	
	public UpdateProductPresenter(UpdateProductViewModel model) {
		this.model = model;
	}
	
	public UpdateProductViewModel getModel() {
		return model;
	}

	@Override
	public void present(UpdateProductOutputData outData) {
		model.message = outData.message;
		model.success = outData.success;
		if(outData.updatedProduct != null) {
			ProductViewDTO updatedProduct = convertToViewDTO(outData.updatedProduct);
			model.updatedProduct = updatedProduct;
		}
	}

	private ProductViewDTO convertToViewDTO(ProductDTO updatedProduct) {
		ProductViewDTO dto = new ProductViewDTO();
		
		dto.id = String.valueOf(updatedProduct.id);
		dto.name = updatedProduct.name;
		dto.description = updatedProduct.description;
		dto.price = String.valueOf(updatedProduct.price);
		dto.stockQuantity = String.valueOf(updatedProduct.stockQuantity);
		dto.imageUrl = updatedProduct.imageUrl;
		dto.categoryId = String.valueOf(updatedProduct.stockQuantity);
		dto.categoryName = updatedProduct.categoryName; 
		
		return dto;
	}
	
	
	
	
}
