package domain.entities;

public class Category {
	private int id;
    private String name;

    // Constructor để tạo mới (đã được UseCase validate)
    public Category(String name) {
        this.name = name;
    }

    // Constructor để tải từ CSDL
    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    // --- Getters / Setters ---
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

	public void setName(String name) {
		this.name = name;
	}
}
