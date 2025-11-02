package adapters.updateUserRole;

import application.dtos.updateRoleUser.UpdateUserRoleOutputData;
import application.dtos.updateRoleUser.UserDTO;
import application.ports.out.updateUserRole.UpdateUserRoleOutputBoundary;
import domain.entities.Role;

public class UpdateUserRolePresenter implements UpdateUserRoleOutputBoundary{
	private UpdateUserRoleModel model;
	
	public UpdateUserRolePresenter(UpdateUserRoleModel model) {
		this.model = model;
	}
	
	public UpdateUserRoleModel getModel() {
		return model;
	}
	
	@Override
	public void present(UpdateUserRoleOutputData outData) {
		model.success = outData.success;
		model.message = outData.message;
		model.updatedUser = convertToViewDto(outData.updatedUser);
	}

	private UserViewDTO convertToViewDto(UserDTO updatedUser) {
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
