package adapters.deleteProduct;

import application.dtos.deleteProduct.DeleteProductOutputData;
import application.ports.out.deleteProduct.DeleteProductOutputBoundary;

public class DeleteProductPresenter implements DeleteProductOutputBoundary {
	private DeleteProductViewModel model;

	public DeleteProductPresenter() {
		
	}
	
	public DeleteProductPresenter(DeleteProductViewModel model) {
		this.model = model;
	}
	
	public DeleteProductViewModel getModel() {
		return model;
	}

	@Override
	public void present(DeleteProductOutputData outData) {
		model.success = outData.success;
		model.message = outData.message;
	}
}
