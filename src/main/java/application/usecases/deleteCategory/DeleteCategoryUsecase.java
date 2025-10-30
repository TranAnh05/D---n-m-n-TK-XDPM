package application.usecases.deleteCategory;

import application.dtos.deleteCategory.DeleteCategoryDTO;
import application.dtos.deleteCategory.DeleteCategoryInputData;
import application.dtos.deleteCategory.DeleteCategoryOutputData;
import application.ports.in.deleteCategory.DeleteCategoryInputBoundary;
import application.ports.out.deleteCategory.DeleteCategoryDAO;
import application.ports.out.deleteCategory.DeleteCategoryOutputBoundary;
import application.ports.out.deleteCategory.ProductRepository;
import domain.entities.Category;

public class DeleteCategoryUsecase implements DeleteCategoryInputBoundary{
	private DeleteCategoryDAO dao;
	private DeleteCategoryOutputBoundary outBoundary;
	private ProductRepository proDAO;
	private DeleteCategoryOutputData outData;
	
	public DeleteCategoryUsecase() {
		
	}
	
	public DeleteCategoryUsecase(DeleteCategoryDAO dao, DeleteCategoryOutputBoundary outBoundary, ProductRepository proDAO) {
		this.dao = dao;
		this.outBoundary = outBoundary;
		this.proDAO = proDAO;
	}
	
	public DeleteCategoryOutputData getOutData() {
		return outData;
	}

	@Override
	public void execute(DeleteCategoryInputData data) {
		outData = new DeleteCategoryOutputData();
		
		try {
			// 1. Kiểm tra Category tồn tại
			DeleteCategoryDTO categoryDAOToDelete = dao.findById(data.id);
			Category categoryBusToDelete = convertToBusData(categoryDAOToDelete);
			if(categoryBusToDelete == null) {
				outData.success = false;
				outData.message = "Không tìm thấy loại sản phẩm để xóa.";
				outBoundary.present(outData);
				return;
			}
			
			// 2. Kiểm tra nghiệp vụ (Quan trọng): Category có đang được sử dụng không
			int productCount = proDAO.countByCategoryById(data.id);
			if (productCount > 0) {
				outData.success = false;
                outData.message = "Không thể xóa. Loại sản phẩm này đang chứa " + productCount + " sản phẩm.";
                outBoundary.present(outData);
                return;
			}
			
			// 3. Xóa
			if(dao.deleteById(data.id)) {
				// 4. Báo cáo thành công
				outData.success = true;
				outData.message = "Đã xóa thành công loại sản phẩm: " + categoryBusToDelete.getName();
				outBoundary.present(outData);
			}
			
		} catch (Exception e) {
			// 5. Xử lý Lỗi Hệ thống
			outData.message = "Đã xảy ra lỗi hệ thống. Vui lòng thử lại.";
            // (Log e.getMessage() ở đây)
			outBoundary.present(outData);
		}
	}

	private Category convertToBusData(DeleteCategoryDTO categoryDAOToDelete) {
		Category category = new Category(categoryDAOToDelete.id, categoryDAOToDelete.name);
		return category;
	}
}
