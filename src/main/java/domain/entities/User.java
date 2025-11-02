package domain.entities;

public class User {
	private int id;
    private String email;
    private String passwordHash; // Quan trọng: CHỈ lưu mật khẩu đã được băm
    private String fullName;
    private String address;
    private Role role; 
    private boolean isBlocked;
    
    public User() {
    	
    }
    
	public User(int id, String email, String passwordHash, String fullName, String address, Role role, boolean isBlocked) {
		super();
		this.id = id;
		this.email = email;
		this.passwordHash = passwordHash;
		this.fullName = fullName;
		this.address = address;
		this.role = role;
		this.isBlocked = isBlocked;
	}
	
	// Constructor để tạo mới
    public User(String email, String passwordHash, String fullName, String address, Role role) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.fullName = fullName;
        this.address = address;
        this.role = role;
        this.isBlocked = false;
    }
    
    // --- Getters (Không nên có Setters cho mọi thứ để đảm bảo an toàn) ---
    public int getId() { return id; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    public String getFullName() { return fullName; }
    public String getAddress() { return address; }
    public Role getRole() { return role; }
    public boolean getIsBLocked() { return isBlocked; }
    
    // --- Validation 
    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        // (Trong thực tế, nên dùng Regex để kiểm tra định dạng)
        return email.contains("@"); 
    }
    
    public static boolean isValidPassword(String password) {
        return password != null && password.trim().length() >= 6;
    }

	public void setRole(Role role) {
		this.role = role;
	} 
	
	public void setIsBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}
}
