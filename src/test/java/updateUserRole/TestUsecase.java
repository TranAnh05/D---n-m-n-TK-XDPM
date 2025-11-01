package updateUserRole;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import MockDB.updateUserRole.MockUserRepository;
import adapters.updateUserRole.UpdateUserRoleModel;
import adapters.updateUserRole.UpdateUserRolePresenter;
import application.dtos.updateRoleUser.UpdateUserRoleInputData;
import application.usecases.updateUserRole.UpdateUserRoleUsecase;
import domain.entities.Role;

public class TestUsecase {
	@Test
	public void test() {
		UpdateUserRoleInputData data = new UpdateUserRoleInputData();
		data.userIdToUpdate = 2;
		data.newRole = "ADMIN";
		data.currentAdminId = 1;
		
		UpdateUserRoleModel model = new UpdateUserRoleModel();
		UpdateUserRolePresenter presenter = new UpdateUserRolePresenter(model);
		MockUserRepository daoUser = new MockUserRepository();
		
		UpdateUserRoleUsecase usecase = new UpdateUserRoleUsecase(presenter, daoUser);
		usecase.execute(data);
		
		assertEquals(Role.ADMIN, usecase.getOutData().updatedUser.role);
		
		// test presenter
		assertEquals("ADMIN", presenter.getModel().updatedUser.role);
	}
}
