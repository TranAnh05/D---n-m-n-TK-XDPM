package MockDB.updateOrderStatus;

import java.util.ArrayList;
import java.util.List;

import application.dtos.UpdateOrderStatus.UserDataDB;
import application.ports.out.updateOrderStatus.UserRepository;
import domain.entities.Role;

public class MockUserRepository implements UserRepository{
	private List<UserDataDB> list = new ArrayList<UserDataDB>();
	
	public MockUserRepository() {
		UserDataDB user1 = new UserDataDB();
		user1.id = 1;
		user1.email = "admin@gmail.com";
		user1.passwordHash = "123456";
		user1.fullName = "Tran Anh";
		user1.address = "Binh Dinh";
	    user1.role = Role.ADMIN;
	    user1.isBlocked = false;
	    
	    UserDataDB user2 = new UserDataDB();
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
	public UserDataDB findById(int id) {
		for(UserDataDB user : list) {
			if(id == user.id) {
				return user;
			}
		}
		
		return null;
	}

}
