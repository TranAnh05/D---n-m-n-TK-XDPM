package MockDB.viewAllUsers;

import java.util.ArrayList;
import java.util.List;

import application.dtos.ViewAllUser.UserFromDBDTO;
import application.ports.out.viewAllUsers.UserRepository;
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
	    user1.isBlocked = false;
	    
	    list.add(user1);
	    list.add(user2);
	}
	@Override
	public List<UserFromDBDTO> findAll() {
		return list;
	}
	
}
