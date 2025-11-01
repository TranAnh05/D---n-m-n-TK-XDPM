package application.ports.in.updateUserRole;

import application.dtos.updateRoleUser.UpdateUserRoleInputData;

public interface UpdateUserRoleInputBoundary {
	void execute(UpdateUserRoleInputData data);
}
