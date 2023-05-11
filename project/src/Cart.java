import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

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
            BufferedWriter writer = new BufferedWriter(new FileWriter(String.format("./data/cart_For_%s.csv"
                    , user.getUsername()), true));
            for (Item item: items) {
                writer.write(item.getId() + "," + item.getName() + "," + item.getCategory() + "," + item.getPrice()
                        + "," + item.getDescription() + "," + item.getLoyaltyPoints() + "," + item.isInStock() + "\n");
            }
            writer.close();
            System.out.println("Operation done successfully!");
        } catch (IOException e) {
            System.out.println("Error with the operation: " + e.getMessage());
        }
    }

    public Cart(User user, Inventory inventory) {
        this.user = user;
        items = new ArrayList<>();
        this.inventory = inventory;
    }

    public void addItem(String itemName) {
        Item item = inventory.getItemByName(itemName);
        if (item != null) {
            items.add(item);
            saveCart();
            items.clear();
        }
    }

    public void removeItem(String itemName) {
        ArrayList<String> lines = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(String.format("./data/cart_For_%s.csv",
                    user.getUsername())));
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.contains(itemName)) {
                    lines.add(line);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(String.format("./data/cart_For_%s.csv",
                    user.getUsername())));
            for (String l: lines) {
                if (!l.isEmpty()) {
                    writer.write(l + '\n');
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("");
        System.out.println("item is removed successfully!");
        System.out.println("");

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

    public void displayCart(User user) {
        try (BufferedReader reader = new BufferedReader(new FileReader(String.format("./data/cart_For_%s.csv",
        user.getUsername() )));) {
            System.out.println("+----+----------------+----------------+----------+------------------------+-----------------+--------+");
            System.out.println("| ID |      Name      |    Category    |  Price   |       Description      | Loyalty Points  |In Stock|");
            System.out.println("+----+----------------+----------------+----------+------------------------+-----------------+--------+");
            String line;
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                int id = Integer.parseInt(row[0]);
                String name = row[1];
                String category = row[2];
                double price = Double.parseDouble(row[3]);
                String description = row[4];
                int loyaltyPoints = Integer.parseInt(row[5]);
                boolean inStock = Boolean.parseBoolean(row[6]);
    
                System.out.format("| %2d | %-14s | %-14s | $%7.2f | %-22s | %15d | %6s |\n",
                        id, name, category, price, description, loyaltyPoints, inStock ? "Yes" : "No");
            }
    
            System.out.println("+----+----------------+----------------+----------+------------------------+-----------------+--------+");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
//        cart.removeItem("M1");
//        cart.removeItem("c1");
        cart.addItem("M1");
        cart.addItem("c1");
        cart.addItem("M1");
//        cart.addItem("cup cake");
//        cart.addItem("wwala333");
    }
}
