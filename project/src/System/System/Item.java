package System;
public class Item {
    private String name;
    private String category;
    private double price;
    private String description;
    private int loyaltyPoints;
    private int id;
    private boolean inStock;

    public Item(int id,String name, String category, double price, String description, int loyaltyPoints,boolean inStock) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
        this.loyaltyPoints = loyaltyPoints;
        this.inStock = inStock;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }
    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public boolean isInStock() {
        return inStock;
    }
    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }
}

