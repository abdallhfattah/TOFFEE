public class Admin {
    private Inventory inventory;
    
    public Admin(Inventory inventory) {
        this.inventory = inventory;
    }
    
    public void modifyItem(String itemName, String category, double price, String description, int loyaltyPoints,boolean stock) {
        Item item = inventory.getItemByName(itemName);
        if (item != null) {
            item.setCategory(category);
            item.setPrice(price);
            item.setDescription(description);
            item.setLoyaltyPoints(loyaltyPoints);
            item.setInStock(stock);
        } else {
            System.out.println("Item not found.");
        }
    }
}
