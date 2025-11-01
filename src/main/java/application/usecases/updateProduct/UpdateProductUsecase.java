package application.usecases.updateProduct;

import application.dtos.updateProduct.CategoryDTO;
import application.dtos.updateProduct.ProductDTO;
import application.dtos.updateProduct.ProductFromDBDTO;
import application.dtos.updateProduct.UpdateProductInputData;
import application.dtos.updateProduct.UpdateProductOutputData;
import application.ports.in.updateProduct.UpdateProductInputBoundary;
import application.ports.out.updateProduct.CategoryRepository;
import application.ports.out.updateProduct.ProductRepository;
import application.ports.out.updateProduct.UpdateProductOutputBoundary;
import domain.entities.Product;

public class UpdateProductUsecase implements UpdateProductInputBoundary{
	private UpdateProductOutputBoundary outBoundary;
	private ProductRepository daoProduct;
	private CategoryRepository daoCategory;
	private UpdateProductOutputData outData;
	
	public UpdateProductUsecase(UpdateProductOutputBoundary outBoundary, ProductRepository daoProduct, CategoryRepository daoCategory) {
		this.outBoundary = outBoundary;
		this.daoProduct = daoProduct;
		this.daoCategory = daoCategory;
	}
	
	public UpdateProductOutputData getOutData() {
		return outData;
	}

	@Override
	public void execute(UpdateProductInputData data) {
		outData = new UpdateProductOutputData();
		
		try {
			// 1. Kiểm tra sản phẩm có tồn tại không
			ProductFromDBDTO existingProduct = daoProduct.findById(data.id);
			if (existingProduct == null) {
				outData.success = false;
				outData.message = "Không tìm thấy sản phẩm để cập nhật.";
                outBoundary.present(outData);
                return;
            }
			
			// 2. Validation Input (Logic của Tầng 4)
			if (!Product.isValidName(data.name) || 
	                !Product.isValidPrice(data.price) || 
	                !Product.isValidStock(data.stockQuantity)) {
	                
				outData.success = false;
				outData.message = "Dữ liệu không hợp lệ (Tên, Giá, hoặc Số lượng).";
				outBoundary.present(outData);
	            return;
			}    
			
			// 3. Kiểm tra nghiệp vụ (Tên trùng)
			ProductFromDBDTO productWithSameName = daoProduct.findByName(data.name);
			if (productWithSameName != null && productWithSameName.id != data.id) {
				outData.success = false;
				outData.message = "Tên sản phẩm này đã tồn tại.";
				outBoundary.present(outData);
                return;
            }
			
			// 4. Kiểm tra Category
			CategoryDTO categoryData = daoCategory.findById(data.categoryId);
			if (categoryData == null) {
				outData.success = false;
				outData.message = "Loại sản phẩm được chọn không hợp lệ.";
				outBoundary.present(outData);
                return;
            }
			
			// 5. Convert DTO sang Entity
			Product productEntity = new Product(
					data.id, data.name, data.description, data.price,
					data.stockQuantity, data.imageUrl, data.categoryId
	        );
			 
			// 6. Convert Entity sang DTO
			ProductFromDBDTO dataToUpdate = mapEntityToData(productEntity);
			
			// 7. Lưu vào CSDL
			ProductFromDBDTO updatedData = daoProduct.update(dataToUpdate);
			
			// // 8. Báo cáo thành công (Làm giàu DTO)
			outData.success = true;
			outData.message = "Cập nhật sản phẩm thành công!";
			outData.updatedProduct = mapDataToOutputData(updatedData, categoryData);
			outBoundary.present(outData);
			
		} catch (Exception e) {
			// 9. Xử lý Lỗi Hệ thống
			outData.success = false;
			outData.message = "Đã xảy ra lỗi hệ thống. Vui lòng thử lại.";
			outBoundary.present(outData);
		}
		
	}

	private ProductDTO mapDataToOutputData(ProductFromDBDTO updatedData, CategoryDTO categoryData) {
		ProductDTO dto = new ProductDTO();
        dto.id = updatedData.id;
        dto.name = updatedData.name;
        dto.description = updatedData.description;
        dto.price = updatedData.price;
        dto.stockQuantity = updatedData.stockQuantity;
        dto.imageUrl = updatedData.imageUrl;
        dto.categoryId = updatedData.categoryId;
        dto.categoryName = (categoryData != null) ? categoryData.name : "Không rõ";
        return dto;
	}

	private ProductFromDBDTO mapEntityToData(Product productEntity) {
		ProductFromDBDTO data = new ProductFromDBDTO(
				productEntity.getId(), 
				productEntity.getName(), 
				productEntity.getDescription(),
				productEntity.getPrice(), 
				productEntity.getStockQuantity(), 
				productEntity.getImageUrl(),
				productEntity.getCategoryId()
	    );
		
        return data;
	}
}
