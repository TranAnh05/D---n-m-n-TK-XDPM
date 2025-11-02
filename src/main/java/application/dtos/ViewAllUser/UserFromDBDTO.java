package application.dtos.ViewAllUser;

import domain.entities.Role;

public class UserFromDBDTO {
	public int id;
    public String email;
    public String passwordHash; // <-- Repo cần trường này
    public String fullName;
    public String address;
    public Role role;
    public boolean isBlocked;
}
