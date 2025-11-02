package application.ports.out.UnlockUser;

import application.dtos.UnlockUser.UnlockUserOutputData;

public interface UnlockUserOutputBoundary {
	void present(UnlockUserOutputData outData);
}
