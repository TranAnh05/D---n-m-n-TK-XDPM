package application.ports.out.viewAllUsers;

import application.dtos.ViewAllUser.ViewAllUserOutputData;

public interface ViewAllUsersOutputBoundary {
	void present(ViewAllUserOutputData outData);
}
