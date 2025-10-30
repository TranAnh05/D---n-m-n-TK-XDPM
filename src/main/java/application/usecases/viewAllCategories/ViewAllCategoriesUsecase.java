package application.usecases.viewAllCategories;

import java.util.ArrayList;
import java.util.List;

import application.dtos.viewAllCategories.ViewAllCategoriesDTO;
import application.dtos.viewAllCategories.ViewAllCategoriesOutputData;
import application.ports.in.viewAllCategories.ViewAllCategoriesInputBoundary;
import application.ports.out.viewAllCategories.ViewAllCategoriesDAO;
import application.ports.out.viewAllCategories.ViewAllCategoriesOutputBoundary;
import domain.entities.Category;

public class ViewAllCategoriesUsecase implements ViewAllCategoriesInputBoundary {
	private ViewAllCategoriesOutputBoundary outBoundary;
	private ViewAllCategoriesDAO dao;
	private ViewAllCategoriesOutputData outData;
	
	public ViewAllCategoriesUsecase( ViewAllCategoriesOutputBoundary outBoundary, ViewAllCategoriesDAO dao) {
		this.outBoundary = outBoundary;
		this.dao = dao;
	}
	
	public ViewAllCategoriesOutputData getOutData() {
		return outData;
	}
	
	@Override
	public void execute() {
		outData = new ViewAllCategoriesOutputData();
		try {
            // 1. Gọi CSDL (qua Repository)
            List<ViewAllCategoriesDTO> categoriesDB = dao.findAll();
            
            // convert sang du lieu entity 
            List<Category> categories = convertToBusinessData(categoriesDB);

            // 2. Xử lý logic nghiệp vụ
            if (categories.isEmpty()) {
            	outData.message = "Chưa có loại sản phẩm nào.";
            }
            
            List<ViewAllCategoriesDTO> viewCategories =  convertToViewData(categories);
            outData.success = true;
            outData.categories = viewCategories;

        } catch (Exception e) {
            // 3. Xử lý lỗi (nếu CSDL sập)
        	outData.success = false;
        	outData.message = "Lỗi hệ thống: Không thể tải danh mục.";
            // (Log lỗi e.getMessage() ở đây)
        }
        
        // 4. Gửi cho Presenter
		outBoundary.present(outData);
	}

	private List<ViewAllCategoriesDTO> convertToViewData(List<Category> categories) {
		List<ViewAllCategoriesDTO> viewCategories = new ArrayList<>();
		for(Category c : categories) {
			ViewAllCategoriesDTO dto = new ViewAllCategoriesDTO();
			dto.id = c.getId();
			dto.name = c.getName();
			viewCategories.add(dto);
		}
		return viewCategories;
	}

	private List<Category> convertToBusinessData(List<ViewAllCategoriesDTO> categoriesDB) {
		List<Category> categories = new ArrayList<>();
		for(ViewAllCategoriesDTO dto : categoriesDB) {
			Category c = new Category(dto.id, dto.name);
			categories.add(c);
		}
		return categories;
	}
	
	
	
}
