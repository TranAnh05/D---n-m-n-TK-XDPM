package adapters.viewAllProducts;

import java.util.ArrayList;
import java.util.List;

import application.dtos.viewAllProducts.ProductDTO;
import application.dtos.viewAllProducts.ViewAllProductsOutputData;
import application.ports.out.viewAllProducts.ViewAllProductsOutputBoundary;

public class ViewAllProductsPresenter implements ViewAllProductsOutputBoundary{
	private ViewAllProductsModel model;
	
	public ViewAllProductsPresenter() {
		
	}
	
	public ViewAllProductsPresenter(ViewAllProductsModel model) {
		this.model = model;
	}
	
	public ViewAllProductsModel getModel() {
		return model;
	}
	
	
	@Override
	public void present(ViewAllProductsOutputData outData) {
		model.success = outData.success;
		model.message = outData.message;
		List<ProductsViewDTO> productsViewDTO = convertToViewDTOs(outData.products);
		model.products = productsViewDTO;
	}

	private List<ProductsViewDTO> convertToViewDTOs(List<ProductDTO> products) {
		List<ProductsViewDTO> list = new ArrayList<ProductsViewDTO>();
		
		for(ProductDTO pDto : products) {
			ProductsViewDTO viewDto = new ProductsViewDTO();
			
			viewDto.id = String.valueOf(pDto.id);
			viewDto.name = pDto.name;
			viewDto.description = pDto.description;
			viewDto.price = String.valueOf(pDto.price);
			viewDto.stockQuantity = String.valueOf(pDto.stockQuantity);
            viewDto.imageUrl = pDto.imageUrl;
            viewDto.categoryId = String.valueOf(pDto.stockQuantity);
            viewDto.categoryName = pDto.categoryName; 
            
            list.add(viewDto);
		}
		return list;
	}
}
