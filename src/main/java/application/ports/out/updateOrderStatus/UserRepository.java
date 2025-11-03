package application.ports.out.updateOrderStatus;

import application.dtos.UpdateOrderStatus.UserDataDB;

public interface UserRepository {
	UserDataDB findById(int id);
}
