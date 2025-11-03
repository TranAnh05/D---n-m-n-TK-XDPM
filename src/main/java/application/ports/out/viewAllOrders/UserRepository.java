package application.ports.out.viewAllOrders;

import java.util.List;

import application.dtos.viewAllOrders.UserDataDB;

public interface UserRepository {
	List<UserDataDB> findAll();
}
