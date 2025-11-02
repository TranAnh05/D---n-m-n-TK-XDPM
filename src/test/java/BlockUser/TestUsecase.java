package BlockUser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import MockDB.BlockUser.MockUserRepository;
import adapters.BlockUser.BlockUserModel;
import adapters.BlockUser.BlockUserPresenter;
import application.dtos.BLockUser.BlockUserInputData;
import application.usecases.BlockUser.BlockUserUsecase;

public class TestUsecase {
	@Test
	public void test() {
		BlockUserInputData data = new BlockUserInputData();
		data.userIdToBlock = 2;
		data.currentAdminId = 1;
		
		BlockUserModel model = new BlockUserModel();
		BlockUserPresenter presenter = new BlockUserPresenter(model);
		MockUserRepository daoUser = new MockUserRepository();
		
		BlockUserUsecase usecase = new BlockUserUsecase(presenter, daoUser);
		
		usecase.execute(data);
		
		assertEquals(true, usecase.getOutData().updatedUser.isBlocked);
		
		// test presenter
		assertEquals("true", presenter.getModel().updatedUser.isBlocked);
	}
}
