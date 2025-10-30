package domain.entities;

public class Product {
	private long id;
    private String name;
    private String description;
    private double price;
    private int stockQuantity;
    private String imageUrl;
    private Category category;
    
 // Constructor (đã được UseCase validate)
    public Product(long id, String name, double price, int stockQuantity, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.category = category;
    }
    
	// (Constructor cho tạo mới, không cần ID)
    public Product(String name, double price, int stockQuantity, Category category) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.category = category;
    }
    
    //  gettes / setters
    public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public double getPrice() {
		return price;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public Category getCategory() {
		return category;
	}

    // --- Validation (Theo logic của bạn) ---
    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }
    
    public static boolean isValidPrice(double price) {
        return price >= 0;
    }
    
    public static boolean isValidStock(int stock) {
        return stock >= 0;
    }
    
    public static boolean isValidCategory(Category category) {
        return category != null;
    }
}
