package application.usecases.updateUserRole;

import application.dtos.updateRoleUser.UpdateUserRoleInputData;
import application.dtos.updateRoleUser.UpdateUserRoleOutputData;
import application.dtos.updateRoleUser.UserDTO;
import application.dtos.updateRoleUser.UserFromDBDTO;
import application.ports.in.updateUserRole.UpdateUserRoleInputBoundary;
import application.ports.out.updateUserRole.UpdateUserRoleOutputBoundary;
import application.ports.out.updateUserRole.UserRepository;
import domain.entities.Role;
import domain.entities.User;

public class UpdateUserRoleUsecase implements UpdateUserRoleInputBoundary{
	private UpdateUserRoleOutputBoundary outBoundary;
	private UserRepository daoUser;
	private UpdateUserRoleOutputData outputData;
	
	public UpdateUserRoleUsecase() {
		
	}
	
	public UpdateUserRoleUsecase(UpdateUserRoleOutputBoundary outBoundary, UserRepository daoUser) {
		this.outBoundary = outBoundary;
		this.daoUser = daoUser;
	}

	public UpdateUserRoleOutputData getOutData() {
		return outputData;
	}

	@Override
	public void execute(UpdateUserRoleInputData data) {
		outputData = new UpdateUserRoleOutputData();
		
		try {
			// 1. Chuyển đổi String input -> Enum (Validation)
			Role newRole;
			
			try {
                // Đảm bảo String đầu vào hợp lệ
                newRole = Role.valueOf(data.newRole.toUpperCase());
            } catch (Exception e) {
                outputData.success = false;
                outputData.message = "Vai trò được chọn không hợp lệ.";
                outBoundary.present(outputData);
                return;
            }
			
			// 2. Kiểm tra nghiệp vụ (Admin tu sua role cua minh)
			if (data.userIdToUpdate == data.currentAdminId) {
                outputData.success = false;
                outputData.message = "Không thể tự thay đổi vai trò của chính mình.";
                outBoundary.present(outputData);
                return;
            }
			
			// 3. Tim UserData
			UserFromDBDTO userData = daoUser.findById(data.userIdToUpdate);
			if (userData == null) {
                outputData.success = false;
                outputData.message = "Không tìm thấy người dùng để cập nhật.";
                outBoundary.present(outputData);
                return;
            }
			
			// 4. Convert dto sang entity
			
			User userEntity = convertToEntity(userData);
			userEntity.setRole(newRole);
			
			// 5. Convert entity sang daoDto
			UserFromDBDTO userDBDTO = convertToDBData(userEntity);
			
			// 6. Luu vao csdl
			UserFromDBDTO updateUserDB = daoUser.update(userDBDTO);
			
			// 7. Báo cáo thành công (Chuyển đổi sang DTO an toàn)
            outputData.success = true;
            outputData.message = "Cập nhật vai trò thành công!";
            outputData.updatedUser = mapUserDataToOutputData(updateUserDB);
            outBoundary.present(outputData);
		} catch (Exception e) {
			// 7. Xử lý Lỗi Hệ thống
            outputData.success = false;
            outputData.message = "Đã xảy ra lỗi hệ thống. Vui lòng thử lại.";
            outBoundary.present(outputData);
		}
	}

	private UserDTO mapUserDataToOutputData(UserFromDBDTO updateUserDB) {
		UserDTO dto = new UserDTO();
		
		dto.id = updateUserDB.id;
		dto.email = updateUserDB.email;
		dto.fullName = updateUserDB.fullName;
		dto.address = updateUserDB.address;
		dto.role = updateUserDB.role;
		
		return dto;
	}

	private UserFromDBDTO convertToDBData(User userEntity) {
		UserFromDBDTO dto = new UserFromDBDTO();
		
		dto.id = userEntity.getId();
		dto.email = userEntity.getEmail();
		dto.passwordHash = userEntity.getPasswordHash();
		dto.fullName = userEntity.getFullName();
		dto.address = userEntity.getAddress();
		dto.role = userEntity.getRole();
		
		return dto;
	}

	private User convertToEntity(UserFromDBDTO userData) {
		User user = new User(userData.id, userData.email, userData.passwordHash, userData.fullName, userData.address, userData.role);
		return user;
	}
	
}
