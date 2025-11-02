package adapters.UnlockUser;
import application.dtos.UnlockUser.UnlockUserOutputData;
import application.dtos.UnlockUser.UserDTO;
import application.ports.out.UnlockUser.UnlockUserOutputBoundary;
import domain.entities.Role;

public class UnlockUserPresenter implements UnlockUserOutputBoundary{
	private UnlockUserModel model;
	
	public UnlockUserPresenter() {
		
	}
	
	public UnlockUserPresenter(UnlockUserModel model) {
		this.model = model;
	}
	
	public UnlockUserModel getModel() {
		return model;
	}

	@Override
	public void present(UnlockUserOutputData outData) {
		model.message = outData.message;
		model.success = outData.success;
		model.updatedUser = convertToViewData(outData.updatedUser);
	}

	private UserViewDTO  convertToViewData(UserDTO updatedUser) {
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

