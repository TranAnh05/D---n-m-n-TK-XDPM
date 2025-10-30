package application.usecases.updateCategory;

import application.dtos.UpdateCategory.UpdateCategoryDTO;
import application.dtos.UpdateCategory.UpdateCategoryInputData;
import application.dtos.UpdateCategory.UpdateCategoryOutputData;
import application.ports.in.UpdateCategory.UpdateCategoryInputBoundary;
import application.ports.out.UpdateCategory.UpdateCategoryDAO;
import application.ports.out.UpdateCategory.UpdateCategoryOutputBoundary;
import domain.entities.Category;

public class UpdateCategoryUsecase implements UpdateCategoryInputBoundary{
	private UpdateCategoryDAO dao;
	private UpdateCategoryOutputBoundary outBoundary;
	private UpdateCategoryOutputData outData;
	
	public UpdateCategoryUsecase() {
		
	}
	
	public UpdateCategoryUsecase(UpdateCategoryDAO dao, UpdateCategoryOutputBoundary outBoundary) {
		this.dao = dao;
		this.outBoundary = outBoundary;
	}
	
	public UpdateCategoryOutputData getOutData() {
		return outData;
	}
	
	

	@Override
	public void execute(UpdateCategoryInputData data) {
		outData = new UpdateCategoryOutputData();
		
		try {
            // --- 1. Validation Input (Ngăn chặn dữ liệu rác) ---
            if (!Category.isValidName(data.newName)) {
                outData.success = false;
                outData.message = "Tên loại sản phẩm không được để trống.";
                outBoundary.present(outData);
                return;
            }

            // --- 2. Tìm đối tượng ---
            UpdateCategoryDTO categoryDAOToUpdate = dao.findById(data.id);
            if (categoryDAOToUpdate == null) {
            	outData.success = false;
            	outData.message = "Không tìm thấy loại sản phẩm để cập nhật.";
                outBoundary.present(outData);
                return;
            }

            // --- 3. Xử lý logic nghiệp vụ ---
            // Chỉ kiểm tra nghiệp vụ khi tên thực sự thay đổi
            Category categoryToUpdate = convertToBusinessData(categoryDAOToUpdate);
            boolean isNameChanged = !categoryToUpdate.getName().equals(data.newName);

            if (isNameChanged) {
                // Kiểm tra nghiệp vụ (Tên trùng)
            	UpdateCategoryDTO existingByName = dao.findByName(data.newName);
                if (existingByName != null) { 
                	outData.success = false;
                    outData.message = "Tên loại sản phẩm này đã tồn tại.";
                    outBoundary.present(outData);
                    return;
                }
                
                // Cập nhật Entity
                categoryToUpdate.setName(data.newName);
            }
            // (Nếu tên không đổi, chúng ta không cần làm gì, cứ tiếp tục)
            // --- 4. Lưu vào CSDL 
            UpdateCategoryDTO updatedCategoryDTO = ConvertToDAOData(categoryToUpdate);
            UpdateCategoryDTO updatedCategory = dao.update(updatedCategoryDTO);

            // --- 5. Báo cáo thành công ---
            outData.success = true;
            outData.message = "Cập nhật thành công!";
            outData.updatedCategory = updatedCategory;
            outBoundary.present(outData);

        } catch (Exception e) {
            // --- 6. Xử lý Lỗi Hệ thống (Unhappy Path) ---
            // (Ví dụ: CSDL sập, NullPointerException, v.v.)
            outData.success = false;
            outData.message = "Đã xảy ra lỗi hệ thống. Vui lòng thử lại.";
            // (Trong thực tế, chúng ta sẽ log e.getMessage() vào file log)
            outBoundary.present(outData);
        }
	}

	private UpdateCategoryDTO ConvertToDAOData(Category categoryToUpdate) {
		UpdateCategoryDTO dto = new UpdateCategoryDTO();
		dto.id = categoryToUpdate.getId();
		dto.name = categoryToUpdate.getName();
		return dto;
	}

	private Category convertToBusinessData(UpdateCategoryDTO categoryDAO) {
		Category c = new Category(categoryDAO.id, categoryDAO.name);
		return c;
	}

}
