package adapters.BlockUser;

import application.dtos.BLockUser.BlockUserOutputData;
import application.dtos.BLockUser.UserDTO;
import application.ports.out.BlockUser.BlockUserOutputBoundary;
import domain.entities.Role;

public class BlockUserPresenter implements BlockUserOutputBoundary{
	private BlockUserModel model;
	
	public BlockUserPresenter() {
		
	}
	
	public BlockUserPresenter(BlockUserModel model) {
		this.model = model;
	}
	
	public BlockUserModel getModel() {
		return model;
	}

	@Override
	public void present(BlockUserOutputData outData) {
		model.message = outData.message;
		model.success = outData.success;
		model.updatedUser = convertToViewData(outData.updatedUser);
	}

	private UserViewDTO convertToViewData(UserDTO updatedUser) {
		UserViewDTO viewDto = new UserViewDTO();
		
		viewDto.id = String.valueOf(updatedUser.id);
		viewDto.email = updatedUser.email;
		viewDto.fullName = updatedUser.fullName;
		viewDto.address = updatedUser.address;
		viewDto.role = convertRoleToString(updatedUser.role);
		viewDto.isBlocked = String.valueOf(updatedUser.isBlocked);
		
		return viewDto;
	}

	private String convertRoleToString(Role role) {
		if(role == null) {
			return "Unknown";
		}
		
		switch (role) {
			case ADMIN:
				return "ADMIN";
			case CUSTOMER:
				return "CUSTOMER";
			default:
				return "Unknown";
		}
	}

}
