package viewAllUsers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import MockDB.viewAllUsers.MockUserRepository;
import adapters.viewAllUsers.ViewAllUsersModel;
import adapters.viewAllUsers.ViewAllUsersPresenter;
import application.usecases.viewAllUsers.ViewAllUsersUsecase;
import domain.entities.Role;

public class TestUsecase {
	@Test
	public void test() {
		ViewAllUsersModel model = new ViewAllUsersModel();
		ViewAllUsersPresenter presenter = new ViewAllUsersPresenter(model);
		MockUserRepository daoUser = new MockUserRepository();
		ViewAllUsersUsecase usecase = new ViewAllUsersUsecase(presenter, daoUser);
		usecase.execute();
		
		assertEquals(Role.ADMIN, usecase.getOutData().users.get(0).role);
		
		// test presenter
		assertEquals("ADMIN", presenter.getModel().users.get(0).role);
	}
}
