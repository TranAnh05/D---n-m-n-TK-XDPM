package MockDB.BlockUser;

import java.util.ArrayList;
import java.util.List;

import application.dtos.BLockUser.UserFromDBDTO;
import application.ports.out.BlockUser.UserRepository;
import domain.entities.Role;

public class MockUserRepository implements UserRepository{
	private List<UserFromDBDTO> list = new ArrayList<UserFromDBDTO>();
	
	public MockUserRepository() {
		UserFromDBDTO user1 = new UserFromDBDTO();
		user1.id = 1;
		user1.email = "admin@gmail.com";
		user1.passwordHash = "123456";
		user1.fullName = "Tran Anh";
		user1.address = "Binh Dinh";
	    user1.role = Role.ADMIN;
	    user1.isBlocked = false;
	    
	    UserFromDBDTO user2 = new UserFromDBDTO();
	    user2.id = 2;
	    user2.email = "customer@gmail.com";
	    user2.passwordHash = "123456";
	    user2.fullName = "Tran Anh 02";
	    user2.address = "Binh Dinh";
	    user2.role = Role.CUSTOMER;
	    user2.isBlocked = false;
	    
	    list.add(user1);
	    list.add(user2);
	}
	
	@Override
	public UserFromDBDTO update(UserFromDBDTO user) {
		int length = list.size();
		for(int i = 0; i < length; i++) {
			if(list.get(i).id == user.id) {
				list.set(i, user);
				return user;
			}
		}
		return null;
	}

	@Override
	public UserFromDBDTO findById(int id) {
		for(UserFromDBDTO dto : list) {
			if(id == dto.id ) {
				return dto;
			}
		}
		return null;
	}

}
