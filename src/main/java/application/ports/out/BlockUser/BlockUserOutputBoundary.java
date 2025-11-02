package application.ports.out.BlockUser;

import application.dtos.BLockUser.BlockUserOutputData;

public interface BlockUserOutputBoundary {
	void present(BlockUserOutputData outData);
}
