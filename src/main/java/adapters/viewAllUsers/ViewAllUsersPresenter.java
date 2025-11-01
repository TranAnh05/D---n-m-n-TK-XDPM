package adapters.viewAllUsers;

import java.util.ArrayList;
import java.util.List;

import application.dtos.ViewAllUser.UserDTO;
import application.dtos.ViewAllUser.ViewAllUserOutputData;
import application.ports.out.viewAllUsers.ViewAllUsersOutputBoundary;
import domain.entities.Role;

public class ViewAllUsersPresenter implements ViewAllUsersOutputBoundary{
	private ViewAllUsersModel model;
	
	public ViewAllUsersPresenter() {
		
	}
	
	public ViewAllUsersPresenter(ViewAllUsersModel model) {
		this.model = model;
	}
	
	public ViewAllUsersModel getModel() {
		return model;
	}
	
	@Override
	public void present(ViewAllUserOutputData outData) {
		model.message = outData.message;
		model.success = outData.success;
		model.users = convertDataToViewDTO(outData.users);
	}

	private List<UserViewDTO> convertDataToViewDTO(List<UserDTO> users) {
		List<UserViewDTO> viewDTOs = new ArrayList<>();
		
		for(UserDTO dto : users) {
			UserViewDTO viewDto = new UserViewDTO();
			
			viewDto.id = String.valueOf(dto.id);
			viewDto.email = dto.email;
			viewDto.fullName = dto.fullName;
			viewDto.address = dto.address;
			viewDto.role = convertRoleToString(dto.role);
			viewDTOs.add(viewDto);
		}
		return viewDTOs;
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
