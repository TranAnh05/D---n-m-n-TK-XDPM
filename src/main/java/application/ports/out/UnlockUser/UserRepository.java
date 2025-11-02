package application.ports.out.UnlockUser;

import application.dtos.UnlockUser.UserFromDBDTO;

public interface UserRepository {
	UserFromDBDTO update(UserFromDBDTO user);
	UserFromDBDTO findById(int id);
}
