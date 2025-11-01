package viewAllProducts;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import MockDB.viewAllProducts.MockCategoryRepository;
import MockDB.viewAllProducts.MockProductRepository;
import adapters.viewAllProducts.ProductsViewDTO;
import adapters.viewAllProducts.ViewAllProductsModel;
import adapters.viewAllProducts.ViewAllProductsPresenter;
import application.ports.out.viewAllProducts.CategoryRepository;
import application.ports.out.viewAllProducts.ProductRepository;
import application.usecases.viewAllProducts.ViewAllProductsUsecase;

public class TestUsecase {
	@Test
	public void test() {
		
		ViewAllProductsModel model = new ViewAllProductsModel();
		ViewAllProductsPresenter presenter = new ViewAllProductsPresenter(model);
		ProductRepository daoProduct = new MockProductRepository();
		CategoryRepository daoCategory = new MockCategoryRepository();
		
		ViewAllProductsUsecase usecase = new ViewAllProductsUsecase(presenter, daoProduct, daoCategory);
		usecase.execute();
		
		assertEquals(2, usecase.getOutData().products.size());
		
		// test presenter
		List<ProductsViewDTO> list = presenter.getModel().products;
		ProductsViewDTO dto = list.get(0);
		
		assertEquals("Laptop", dto.categoryName);
	}
}
