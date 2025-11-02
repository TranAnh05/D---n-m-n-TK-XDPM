package application.usecases.UnlockUser;

import application.dtos.UnlockUser.UnlockUserInputData;
import application.dtos.UnlockUser.UnlockUserOutputData;
import application.dtos.UnlockUser.UserDTO;
import application.dtos.UnlockUser.UserFromDBDTO;
import application.ports.in.UnlockUser.UnlockUserInputBoundary;
import application.ports.out.UnlockUser.UnlockUserOutputBoundary;
import application.ports.out.UnlockUser.UserRepository;
import domain.entities.User;

public class UnlockUserUsecase implements UnlockUserInputBoundary{
	private UnlockUserOutputBoundary outBoundary;
	private UserRepository daoUser;
	private UnlockUserOutputData outputData;
	
	public UnlockUserUsecase() {
		
	}
	
	public UnlockUserUsecase(UnlockUserOutputBoundary outBoundary, UserRepository daoUser) {
		this.outBoundary = outBoundary;
		this.daoUser = daoUser;
	}
	
	public UnlockUserOutputData getOutData() {
		return outputData;
	}

	@Override
	public void execute(UnlockUserInputData data) {
		outputData = new UnlockUserOutputData();
		
		try {
			// 1. Tìm UserData 
            UserFromDBDTO userData = daoUser.findById(data.userIdToUnblock);
            if (userData == null) {
                outputData.success = false;
                outputData.message = "Không tìm thấy người dùng để mở khóa.";
                outBoundary.present(outputData);
                return;
            }
            
            // Chuyen sang Entity
            User userEntity = new User(
					userData.id, userData.email, userData.passwordHash,
	                userData.fullName, userData.address, userData.role, userData.isBlocked);
			userEntity.setIsBlocked(false);
			
			// 3. Chuyen sang DTO
			UserFromDBDTO userDAO = convertToDAOData(userEntity);
			
			// 5 Luu vao csdl
			UserFromDBDTO updatedUser = daoUser.update(userDAO);
			
			// 6. Báo cáo thành công
			outputData.success = true;
            outputData.message = "Khóa tài khoản thành công!";
            outputData.updatedUser = mapUserDataToOutputData(updatedUser);
            outBoundary.present(outputData);
		} catch (Exception e) {
			outputData.success = false;
            outputData.message = "Đã xảy ra lỗi hệ thống. Vui lòng thử lại.";
            outBoundary.present(outputData);
		}
	}

	private UserDTO mapUserDataToOutputData(UserFromDBDTO updatedUser) {
		UserDTO dto = new UserDTO();
		
		dto.id = updatedUser.id;
		dto.email = updatedUser.email;
		dto.fullName = updatedUser.fullName;
		dto.address = updatedUser.address;
		dto.role = updatedUser.role;
		dto.isBlocked = updatedUser.isBlocked;
		
		return dto;
	}

	private UserFromDBDTO convertToDAOData(User userEntity) {
		UserFromDBDTO dto = new UserFromDBDTO();
		
		dto.id = userEntity.getId();
		dto.email = userEntity.getEmail();
		dto.passwordHash = userEntity.getPasswordHash();
		dto.fullName = userEntity.getFullName();
		dto.address = userEntity.getAddress();
		dto.role = userEntity.getRole();
		dto.isBlocked = userEntity.getIsBLocked();
		
		return dto;
	}

}
