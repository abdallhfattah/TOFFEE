import java.util.ArrayList;

public class Cart {
    private ArrayList<Item> items;
    private Inventory inventory;

    public Cart(Inventory inventory) {
        items = new ArrayList<>();
        this.inventory = inventory;
    }

    public void addItem(String itemName) {
        Item item = inventory.getItemByName(itemName);
        if (item != null) {
            items.add(item);
        }
    }

    public void removeItem(String itemName) {
        Item itemToRemove = null;
        for (Item item : items) {
            if (item.getName().equals(itemName)) {
                itemToRemove = item;
                break;
            }
        }
        if (itemToRemove != null) {
            items.remove(itemToRemove);
        }
    }

    public void clearCart() {
        items.clear();
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public double getTotalPrice() {
        double total = 0;
        for (Item item : items) {
            total += item.getPrice();
        }
        return total;
    }

    public void displayCart() {
        System.out.println("+----------------+----------------+----------+------------------------+-----------------+");
        System.out.println("|      Name      |    Category    |  Price   |       Description      | Loyalty Points  |");
        System.out.println("+----------------+----------------+----------+------------------------+-----------------+");
    
        for (Item item : items) {
            System.out.format("| %-14s | %-14s | $%7.2f | %-22s | %15d |\n",
                    item.getName(), item.getCategory(), item.getPrice(), item.getDescription(),
                    item.getLoyaltyPoints());
        }
    
        System.out.println("+----------------+----------------+----------+------------------------+-----------------+");
        System.out.format("|                                      Total: $%7.2f                                 |\n", getTotalPrice());
        System.out.println("+------------------------------------------------------------------------------+");
    }
}
