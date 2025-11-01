package application.usecases.viewAllProducts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import application.dtos.viewAllProducts.CategoryDTO;
import application.dtos.viewAllProducts.ProductDTO;
import application.dtos.viewAllProducts.ProductFromDBDTO;
import application.dtos.viewAllProducts.ViewAllProductsOutputData;
import application.ports.in.viewAllProducts.ViewAllProductsInputBoundary;
import application.ports.out.viewAllProducts.CategoryRepository;
import application.ports.out.viewAllProducts.ProductRepository;
import application.ports.out.viewAllProducts.ViewAllProductsOutputBoundary;
import domain.entities.Product;

public class ViewAllProductsUsecase implements ViewAllProductsInputBoundary{
	private ViewAllProductsOutputBoundary outBoundary;
	private ProductRepository productRepo;
	private CategoryRepository categoryRepo;
	private ViewAllProductsOutputData outData;
	
	public ViewAllProductsUsecase() {
		
	}
	
	public ViewAllProductsUsecase(ViewAllProductsOutputBoundary outBoundary, ProductRepository productRepo, CategoryRepository categoryRepo) {
		this.outBoundary = outBoundary;
		this.productRepo = productRepo;
		this.categoryRepo = categoryRepo;
	}
	
	public ViewAllProductsOutputData getOutData() {
		return outData;
	}

	@Override
	public void execute() {
		outData = new ViewAllProductsOutputData();
		
		try {
			// 1. Lay du lieu tu CSDL
			List<ProductFromDBDTO> productsDto = productRepo.findAll();
			List<CategoryDTO> categoriesDto = categoryRepo.findAll();
			
			// 2. Xu ly truong hop rong
			if (productsDto.isEmpty()) {
                outData.success = true;
                outData.message = "Chưa có sản phẩm nào.";
                outData.products = new ArrayList<>();
                outBoundary.present(outData);
                return;
            }
			
			// 3. Chuẩn bị Map để tra cứu (Tối ưu hiệu suất)
			Map<Integer, CategoryDTO> categoryMap = mapCategoriesToData(categoriesDto);
			
			// 4. Chuyển DTO sang Entity 
			List<Product> productEntities = mapDataToEntities(productsDto);
			
			// 5. Chuyển Entity sang DTO Đầu ra 
			List<ProductDTO> safeOutputList = mapEntitiesToOutputData(productEntities, categoryMap);
			
			// 6. Bao cao thanh cong
			outData.success = true;
			outData.products = safeOutputList;
            outBoundary.present(outData);
			
		} catch (Exception e) {
			// 7. Xu ly loi he thong
			outData.success = false;
			outData.message = "Đã xảy ra lỗi hệ thống khi tải sản phẩm.";
			outData.products = new ArrayList<>(); 
            outBoundary.present(outData);
		}
		
	}

	private List<ProductDTO> mapEntitiesToOutputData(List<Product> productEntities,
			Map<Integer, CategoryDTO> categoryMap) {
		List<ProductDTO> safeOutputList = new ArrayList<>();
		
		for(Product product : productEntities) {
			ProductDTO dto = new ProductDTO();
			
			dto.id = product.getId();
            dto.name = product.getName();
            dto.description = product.getDescription();
            dto.price = product.getPrice();
            dto.stockQuantity = product.getStockQuantity();
            dto.imageUrl = product.getImageUrl();
            dto.categoryId = product.getCategoryId();
            
            CategoryDTO categoryDTO = categoryMap.get(product.getCategoryId());
            
            if(categoryDTO != null) {
            	dto.categoryName = categoryDTO.name;
            }
            else {
            	dto.categoryName = "Không rõ";
            }
            
            safeOutputList.add(dto);
		}
		return safeOutputList;
	}

	private List<Product> mapDataToEntities(List<ProductFromDBDTO> productsDto) {
		List<Product> productEntities = new ArrayList<>();
		
		for(ProductFromDBDTO data : productsDto ) {
			Product p = new Product(
	                data.id, 
	                data.name, 
	                data.description, 
	                data.price,
	                data.stockQuantity, 
	                data.imageUrl, 
	                data.categoryId
            );
			productEntities.add(p);
		}
		return productEntities;
	}

	private Map<Integer, CategoryDTO> mapCategoriesToData(List<CategoryDTO> categoriesDto) {
		Map<Integer, CategoryDTO> categoryMap = new HashMap<>();
        for (CategoryDTO cData : categoriesDto) {
            categoryMap.put(cData.id, cData);
        }
        return categoryMap;
	}

}
