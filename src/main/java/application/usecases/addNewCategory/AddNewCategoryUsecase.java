package application.usecases.addNewCategory;

import application.dtos.addNewCategory.AddNewCategoryInputData;
import application.dtos.addNewCategory.AddNewCategoryOutputData;
import application.ports.in.addNewCategory.AddNewCategoryInputBoundary;
import application.ports.out.addNewCategory.AddNewCategoryOutputBoundary;
import application.ports.out.addNewCategory.AddNewCategoryInterface;
import domain.entities.Category;

public class AddNewCategoryUsecase implements AddNewCategoryInputBoundary{
	private AddNewCategoryOutputBoundary outBoundary;
	private AddNewCategoryInterface dao;
	private AddNewCategoryOutputData outputData;
	
	public AddNewCategoryUsecase(AddNewCategoryOutputBoundary outBoundary, AddNewCategoryInterface dao) {
		this.outBoundary = outBoundary;
		this.dao = dao;
	}
	
	public AddNewCategoryOutputData getOutdata() {
		return outputData;
	}

	@Override
	public void execute(AddNewCategoryInputData input) {
		outputData = new AddNewCategoryOutputData();
		// 1. Validate
		if(!Category.isValidName(input.categoryName)) {
			outputData.success = false;
			outputData.message = "Ten loai san pham khong duoc de trong";
			outBoundary.present(outputData);
			return;
		}
		
		// 2. Kiểm tra nghiệp vụ (Tên trùng)
		if (dao.findByName(input.categoryName) != null) {
            outputData.success = false;
            outputData.message = "Tên loại sản phẩm này đã tồn tại.";
            outBoundary.present(outputData);
            return;
        }
		
		// 3. Tạo Entity (khi mọi thứ đã hợp lệ)
		Category newCategory = new Category(input.categoryName);
		
		// 4. Lưu vào CSDL
		Category savedCategory = dao.save(newCategory);
		
		// 5. Báo cáo thành công
        outputData.success = true;
        outputData.message = "Thêm mới thành công: " + savedCategory.getName();
        outBoundary.present(outputData);
	}
}
