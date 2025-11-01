package application.ports.out.updateUserRole;

import application.dtos.updateRoleUser.UpdateUserRoleOutputData;

public interface UpdateUserRoleOutputBoundary {
	void present(UpdateUserRoleOutputData outData);
}
