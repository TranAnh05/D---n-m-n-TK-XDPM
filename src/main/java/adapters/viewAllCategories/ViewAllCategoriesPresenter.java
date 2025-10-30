package adapters.viewAllCategories;

import java.util.ArrayList;
import java.util.List;

import application.dtos.viewAllCategories.ViewAllCategoriesDTO;
import application.dtos.viewAllCategories.ViewAllCategoriesOutputData;
import application.ports.out.viewAllCategories.ViewAllCategoriesOutputBoundary;

public class ViewAllCategoriesPresenter implements ViewAllCategoriesOutputBoundary {
	private ViewAllCategoriesViewModel viewModel;

    public ViewAllCategoriesPresenter(ViewAllCategoriesViewModel viewModel) {
        this.viewModel = viewModel;
    }
    
    
    public ViewAllCategoriesViewModel getModel() {
    	return viewModel;
    }
	
	
	@Override
	public void present(ViewAllCategoriesOutputData output) {
		viewModel.categories = convertToViewData(output.categories);
		viewModel.message = output.message;
		viewModel.success = output.success;
	}
	
	public List<ViewAllCategoriesViewDTO> convertToViewData(List<ViewAllCategoriesDTO> businessData) {
		List<ViewAllCategoriesViewDTO> viewDtos = new ArrayList<ViewAllCategoriesViewDTO>();
		for(ViewAllCategoriesDTO bData : businessData) {
			ViewAllCategoriesViewDTO dto = new ViewAllCategoriesViewDTO();
			dto.id = String.valueOf(bData.id);
			dto.name = bData.name;
			viewDtos.add(dto);
		}
		return viewDtos;
	}

}
