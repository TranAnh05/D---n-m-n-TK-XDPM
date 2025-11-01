package application.ports.out.updateUserRole;

import application.dtos.updateRoleUser.UserFromDBDTO;

public interface UserRepository {
	UserFromDBDTO update(UserFromDBDTO user);
	UserFromDBDTO findById(int id);
}
