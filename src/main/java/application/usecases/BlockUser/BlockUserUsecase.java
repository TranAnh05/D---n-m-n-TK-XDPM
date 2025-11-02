package application.usecases.BlockUser;

import application.dtos.BLockUser.BlockUserInputData;
import application.dtos.BLockUser.BlockUserOutputData;
import application.dtos.BLockUser.UserDTO;
import application.dtos.BLockUser.UserFromDBDTO;
import application.ports.in.BlockUser.BlockUserInputBoundary;
import application.ports.out.BlockUser.BlockUserOutputBoundary;
import application.ports.out.BlockUser.UserRepository;
import domain.entities.User;

public class BlockUserUsecase implements BlockUserInputBoundary{
	private BlockUserOutputBoundary outBoundary;
	private UserRepository daoUser;
	private BlockUserOutputData outputData;
	
	public BlockUserUsecase() {
		
	}
	
	public BlockUserUsecase(BlockUserOutputBoundary outBoundary, UserRepository daoUser) {
		this.outBoundary = outBoundary;
		this.daoUser = daoUser;
	}
	
	public BlockUserOutputData getOutData() {
		return outputData;
	}
	
	@Override
	public void execute(BlockUserInputData data) {
		outputData = new BlockUserOutputData();
		
		try {
			// 1. Kiểm tra nghiệp vụ (Admin tự khóa)
			if (data.userIdToBlock == data.currentAdminId) {
                outputData.success = false;
                outputData.message = "Không thể tự khóa tài khoản của chính mình.";
                outBoundary.present(outputData);
                return;
            }
			
			// 2. Tìm UserData 
			UserFromDBDTO userData = daoUser.findById(data.userIdToBlock);
			if (userData == null) {
                outputData.success = false;
                outputData.message = "Không tìm thấy người dùng để khóa.";
                outBoundary.present(outputData);
                return;
            }
			
			// 3. Chuyển sang Entity
			User userEntity = new User(
					userData.id, userData.email, userData.passwordHash,
	                userData.fullName, userData.address, userData.role, userData.isBlocked);
			userEntity.setIsBlocked(true);
			
			// 4 Chuyen sang DTO
			UserFromDBDTO userDAO = convertToDAOData(userEntity);
			
			// 5 Luu vao csdl
			UserFromDBDTO updatedUser = daoUser.update(userDAO);
			
			// 6. Báo cáo thành công
			outputData.success = true;
            outputData.message = "Khóa tài khoản thành công!";
            outputData.updatedUser = mapUserDataToOutputData(updatedUser);
            outBoundary.present(outputData);
		} catch (Exception e) {
			// 7. Xử lý Lỗi Hệ thống
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
