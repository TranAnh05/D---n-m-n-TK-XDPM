package application.ports.out.viewAllUsers;

import java.util.List;

import application.dtos.ViewAllUser.UserFromDBDTO;

public interface UserRepository {
	List<UserFromDBDTO> findAll();
}
