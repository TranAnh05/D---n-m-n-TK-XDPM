package application.dtos.updateProduct;

public class ProductFromDBDTO {
	public int id; 
    public String name;
    public String description;
    public double price;
    public int stockQuantity;
    public String imageUrl;
    public int categoryId;
    
    public ProductFromDBDTO() {
    	
    }
    
	public ProductFromDBDTO(int id, String name, String description, double price, int stockQuantity, String imageUrl,
			int categoryId) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.stockQuantity = stockQuantity;
		this.imageUrl = imageUrl;
		this.categoryId = categoryId;
	}
    
    
}
