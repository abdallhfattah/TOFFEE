import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Cart {
    private User user;
    private ArrayList<Item> items;
    private Inventory inventory;

    private void saveCart() {
        if (items.isEmpty()) {
            System.out.println("list empty");
            return;
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(String.format("./src/data/cartFor%s.csv"
                    , user.getUsername()), true));
            for (Item item: items) {
                writer.write(item.getId() + "," + item.getName() + "," + item.getCategory() + "," + item.getPrice()
                        + "," + item.getDescription() + "," + item.getLoyaltyPoints() + "," + item.isInStock() + "\n");
            }
            writer.close();
            System.out.println("Items added successfully!");
        } catch (IOException e) {
            System.out.println("Error adding items: " + e.getMessage());
        }
    }

    public Cart(User user, Inventory inventory) {
        this.user = user;
        items = new ArrayList<>();
        this.inventory = inventory;
    }
    public Cart(Inventory inventory) {
        items = new ArrayList<>();
        this.inventory = inventory;
    }

    public void addItem(String itemName) {
        Item item = inventory.getItemByName(itemName);
        if (item != null) {
            items.add(item);
        }
        saveCart();
    }

    public void removeItem(String itemName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(String.format("./src/data/cartFor%s.csv",
                    user.getUsername())));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line.contains(itemName));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(String.format("./src/data/cartFor%s.csv",
                    user.getUsername())));
            writer.
        } catch (IOException e) {
            e.printStackTrace();
        }

        saveCart();
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
    public static void main(String[] args) throws IOException {
        Inventory inventory = new Inventory();

        Item item1 = new Item(1, "T1", "sweets", 29.99, "fluffy candy", 10,true);
        Item item2 = new Item(2, "M1", "bakery", 9.99, "just molto ", 5,false);
        Item item3 = new Item(2, "c1", "bakery", 14.99, "cup cake", 5,true);
        Item item4 = new Item(2, "w1", "bakery", 4.99, "gums", 0,true);
        inventory.addItem(item1);
        inventory.addItem(item2);
        inventory.addItem(item3);
        inventory.addItem(item4);
        inventory.saveItemsToCSV();

        User user = new User("test", "1234");
        Cart cart = new Cart(user, inventory);
        cart.removeItem("M1");
//        cart.addItem("M1");
//        cart.addItem("C1");
//        cart.addItem("M1");
//        cart.addItem("cup cake");
//        cart.addItem("wwala333");
    }
}
