package application.ports.in.UnlockUser;

import application.dtos.UnlockUser.UnlockUserInputData;

public interface UnlockUserInputBoundary {
	void execute(UnlockUserInputData data);
}
