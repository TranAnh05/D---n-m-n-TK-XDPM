package application.usecases.addNewProduct;

import application.dtos.addNewProduct.AddNewProductDTO;
import application.dtos.addNewProduct.AddNewProductInputData;
import application.dtos.addNewProduct.AddNewProductOutputData;
import application.dtos.addNewProduct.CategoryDTO;
import application.ports.in.addNewProduct.AddNewProductInputBoundary;
import application.ports.out.addNewProduct.AddNewProductOutputBoundary;
import application.ports.out.addNewProduct.CategoryRepository;
import application.ports.out.addNewProduct.ProductRepository;
import domain.entities.Category;
import domain.entities.Product;

public class AddNewProductUsecase implements AddNewProductInputBoundary{
	private AddNewProductOutputBoundary outBoundary;
	private ProductRepository daoProduct;
	private CategoryRepository daoCategory;
	private AddNewProductOutputData outData;
	
	public AddNewProductUsecase(AddNewProductOutputBoundary outBoundary, ProductRepository daoProduct, CategoryRepository daoCategory) {
		this.outBoundary = outBoundary;
		this.daoProduct = daoProduct;
		this.daoCategory = daoCategory;
	}
	
	public AddNewProductOutputData getOutData() {
		return outData;
	}

	@Override
	public void execute(AddNewProductInputData data) {
		outData = new AddNewProductOutputData();
		
		try {
			// 1. Validation Input (Logic của bạn)
            if (!Product.isValidName(data.name) || 
                !Product.isValidPrice(data.price) || 
                !Product.isValidStock(data.stockQuantity)) {
                
                outData.success = false;
                outData.message = "Dữ liệu không hợp lệ (Tên, Giá, hoặc Số lượng).";
                outBoundary.present(outData);
                return;
            }
            
            // 2. Kiểm tra nghiệp vụ (Tên trùng)
            if(daoProduct.findByName(data.name) != null) {
            	 outData.success = false;
            	 outData.message = "Tên sản phẩm này đã tồn tại.";
                 outBoundary.present(outData);
                 return;
            }
            
            //  3. Kiểm tra Category
            CategoryDTO categoryDTO = daoCategory.findById(data.categoryId);
            Category category = convertCategoryToBusinessData(categoryDTO);
            if (category == null) {
            	outData.success = false;
            	outData.message = "Loại sản phẩm được chọn không hợp lệ.";
            	outBoundary.present(outData);
                return;
            }
            
            // 4. Tạo Entity (khi mọi thứ đã hợp lệ)
            Product newProduct = new Product(data.name, data.description, data.price, data.stockQuantity, data.imageUrl, data.categoryId);
            
            // 5. Lưu vào CSDL
            
            AddNewProductDTO savedProductDTO = convertProductToDAOData(newProduct);
            AddNewProductDTO savedProduct = daoProduct.save(savedProductDTO);
            
            // 6. 6. Báo cáo thành công
            outData.success = true;
            outData.message = "Thêm sản phẩm thành công!";
            outData.newProduct = savedProduct;
            outBoundary.present(outData);
		} catch (Exception e) {
			outData.success = false;
			outData.message = "Đã xảy ra lỗi hệ thống. Vui lòng thử lại.";
			outBoundary.present(outData);
		}
	}

	private AddNewProductDTO convertProductToDAOData(Product newProduct) {
		AddNewProductDTO dto = new AddNewProductDTO();
		dto.id = newProduct.getId();
		dto.name = newProduct.getName();
		dto.description = newProduct.getDescription();
		dto.stockQuantity = newProduct.getStockQuantity();
		dto.price = newProduct.getPrice();
		dto.imageUrl = newProduct.getImageUrl();
		dto.categoryId = newProduct.getCategoryId();
		return dto;
	}

	private Category convertCategoryToBusinessData(CategoryDTO dto) {
		Category c = new Category(dto.id, dto.name);
		return c;
	}
}
