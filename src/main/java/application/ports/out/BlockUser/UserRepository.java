package application.ports.out.BlockUser;

import application.dtos.BLockUser.UserFromDBDTO;

public interface UserRepository {
	UserFromDBDTO update(UserFromDBDTO user);
	UserFromDBDTO findById(int id);
}
