package UnlockUser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import MockDB.UnlockUser.MockUserRepository;
import adapters.UnlockUser.UnlockUserModel;
import adapters.UnlockUser.UnlockUserPresenter;
import application.dtos.UnlockUser.UnlockUserInputData;
import application.usecases.UnlockUser.UnlockUserUsecase;

public class TestUsecase {
	@Test
	public void test() {
		UnlockUserInputData data = new UnlockUserInputData();
		data.userIdToUnblock = 2;
		
		UnlockUserModel model = new UnlockUserModel();
		UnlockUserPresenter presenter = new UnlockUserPresenter(model);
		MockUserRepository daoUser = new MockUserRepository();
		
		UnlockUserUsecase usecase = new UnlockUserUsecase(presenter, daoUser);
		
		usecase.execute(data);
		
		assertEquals(false, usecase.getOutData().updatedUser.isBlocked);
		
		// test presenter
		assertEquals("false", presenter.getModel().updatedUser.isBlocked);
	}
}
