package adapters.addNewProduct;
import application.dtos.addNewProduct.AddNewProductDTO;
import application.dtos.addNewProduct.AddNewProductOutputData;
import application.ports.out.addNewProduct.AddNewProductOutputBoundary;

public class AddNewProductPresenter implements AddNewProductOutputBoundary{
	private AddNewProductViewModel model;
	
	public AddNewProductPresenter() {
		
	}
	
	public AddNewProductPresenter(AddNewProductViewModel model) {
		this.model = model;
	}
	
	public AddNewProductViewModel getModel() {
		return model;
	}

	@Override
	public void present(AddNewProductOutputData outData) {
		model.message = outData.message;
		model.success = outData.success;
		AddNewProductViewDTO viewDTO = convertToViewDTO(outData.newProduct);
		model.newProduct = viewDTO;
	}

	private AddNewProductViewDTO convertToViewDTO(AddNewProductDTO newProduct) {
		AddNewProductViewDTO viewDTO = new AddNewProductViewDTO();
		viewDTO.id = String.valueOf(newProduct.id);
		viewDTO.name = newProduct.name;
		viewDTO.description = newProduct.description;
		viewDTO.price = String.valueOf(newProduct.price);
		viewDTO.stockQuantity = String.valueOf(newProduct.stockQuantity);
		viewDTO.imageUrl = newProduct.imageUrl;
		viewDTO.categoryId = String.valueOf(newProduct.categoryId);
		return viewDTO;
	}

}
