package viewAllCategories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import MockDB.viewAllCategories.MockViewAllCategoriesDB;
import adapters.viewAllCategories.ViewAllCategoriesPresenter;
import adapters.viewAllCategories.ViewAllCategoriesViewDTO;
import adapters.viewAllCategories.ViewAllCategoriesViewModel;
import application.dtos.viewAllCategories.ViewAllCategoriesDTO;
import application.usecases.viewAllCategories.ViewAllCategoriesUsecase;

public class TestUsecase {
	@Test
	public void test() {
		ViewAllCategoriesViewModel model = new ViewAllCategoriesViewModel();
		ViewAllCategoriesPresenter presenter = new ViewAllCategoriesPresenter(model);
		MockViewAllCategoriesDB data = new MockViewAllCategoriesDB();
		
		ViewAllCategoriesUsecase usecase = new ViewAllCategoriesUsecase(presenter,data);
		usecase.execute();
		
		List<ViewAllCategoriesDTO> categories = usecase.getOutData().categories;
		
//		test usecase
		assertEquals(2, categories.size());
		
//		test presenter
		presenter.present(usecase.getOutData());
		
		List<ViewAllCategoriesViewDTO> listViewData = presenter.getModel().categories;
		
		String id = "";
		for(ViewAllCategoriesViewDTO dto : listViewData) {
			id = dto.id;
		}
		
		assertEquals("2", id);
	}
}
