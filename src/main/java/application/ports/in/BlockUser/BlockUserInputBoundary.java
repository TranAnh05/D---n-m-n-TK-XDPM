package application.ports.in.BlockUser;

import application.dtos.BLockUser.BlockUserInputData;

public interface BlockUserInputBoundary {
	void execute(BlockUserInputData data);
}
