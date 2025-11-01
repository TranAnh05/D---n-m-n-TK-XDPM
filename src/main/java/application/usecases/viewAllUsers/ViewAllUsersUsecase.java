package application.usecases.viewAllUsers;

import java.util.ArrayList;
import java.util.List;

import application.dtos.ViewAllUser.UserDTO;
import application.dtos.ViewAllUser.UserFromDBDTO;
import application.dtos.ViewAllUser.ViewAllUserOutputData;
import application.ports.in.viewAllUsers.ViewAllUsersInputBoundary;
import application.ports.out.viewAllUsers.UserRepository;
import application.ports.out.viewAllUsers.ViewAllUsersOutputBoundary;
import domain.entities.User;

public class ViewAllUsersUsecase implements ViewAllUsersInputBoundary{
	private ViewAllUsersOutputBoundary outBoundary;
	private UserRepository daoUser;
	private ViewAllUserOutputData outputData;
	
	public ViewAllUsersUsecase() {
		
	}
	
	public ViewAllUsersUsecase(ViewAllUsersOutputBoundary outBoundary, UserRepository daoUser) {
		this.outBoundary = outBoundary;
		this.daoUser = daoUser;
	}
	
	public ViewAllUserOutputData getOutData() {
		return outputData;
	}

	@Override
	public void execute() {
		outputData = new ViewAllUserOutputData();
		
		try {
			// 1. Lay danh sach tu csdl
			List<UserFromDBDTO> userDataList = daoUser.findAll();
			
			if (userDataList.isEmpty()) {
                outputData.success = true;
                outputData.message = "Không tìm thấy người dùng nào.";
                outputData.users = new ArrayList<>();
                outBoundary.present(outputData);
                return;
            }
			
			// 2. Convert dto sang entity
			List<User> userEntities = convertDataToEntity(userDataList);
			
			// 3. Convert entity sang outputDto
			List<UserDTO> userOutputDto = convertDataToOutputDto(userEntities);
			
			// 4. Báo cáo thành công
            outputData.success = true;
            outputData.message = "Danh sách người dùng tìm được!";
            outputData.users = userOutputDto;
            outBoundary.present(outputData);
			
		} catch (Exception e) {
			// 5. Xử lý Lỗi Hệ thống
            outputData.success = false;
            outputData.message = "Đã xảy ra lỗi hệ thống khi tải người dùng.";
            outputData.users = new ArrayList<>();
            outBoundary.present(outputData);
		}
	}

	private List<UserDTO> convertDataToOutputDto(List<User> userEntities) {
		List<UserDTO> safeOutputList = new ArrayList<>();
		
        for (User entity : userEntities) {
        	UserDTO dto = new UserDTO();
        	
            dto.id = entity.getId();
            dto.email = entity.getEmail();
            dto.fullName = entity.getFullName();
            dto.address = entity.getAddress();
            dto.role = entity.getRole();
            
            safeOutputList.add(dto);
        }
        
        return safeOutputList;
	}

	private List<User> convertDataToEntity(List<UserFromDBDTO> userDataList) {
		List<User> userEntities = new ArrayList<>();
        for (UserFromDBDTO data : userDataList) {
            userEntities.add(new User(
                data.id, data.email, data.passwordHash,
                data.fullName, data.address, data.role
            ));
        }
        return userEntities;
	}
	
	
}
