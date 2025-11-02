package application.dtos.UnlockUser;

import domain.entities.Role;

public class UserFromDBDTO {
	public int id;
    public String email;
    public String passwordHash; 
    public String fullName;
    public String address;
    public Role role;
    public boolean isBlocked;
}
