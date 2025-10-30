package deleteCategory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import MockDB.deleteCategory.MockCategoryRepository;
import MockDB.deleteCategory.MockProductRepository;
import adapters.deleteCategory.DeleteCategoryPresenter;
import adapters.deleteCategory.DeleteCategoryViewModel;
import application.dtos.deleteCategory.DeleteCategoryInputData;
import application.ports.out.deleteCategory.DeleteCategoryDAO;
import application.ports.out.deleteCategory.ProductRepository;
import application.usecases.deleteCategory.DeleteCategoryUsecase;

public class TestUsecase {
	@Test
	public void test() {
		DeleteCategoryInputData data = new DeleteCategoryInputData(1);
		DeleteCategoryViewModel model = new DeleteCategoryViewModel();
		DeleteCategoryDAO dao = new MockCategoryRepository();
		DeleteCategoryPresenter presenter = new DeleteCategoryPresenter(model);
		ProductRepository proRepo = new MockProductRepository();
		
		DeleteCategoryUsecase usecase = new DeleteCategoryUsecase(dao,presenter,  proRepo);
		usecase.execute(data);
		
		// Không thể xóa vì trong product có loại sản phẩm này
		boolean result = usecase.getOutData().success;
		assertEquals(true, result);
	}
}
