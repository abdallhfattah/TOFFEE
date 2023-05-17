package User;
import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import System.Inventory;
import System.Item;

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
            BufferedWriter writer = new BufferedWriter(new FileWriter(String.format("./src/data/cart_For_%s.csv"
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
        LineBreak();
        Item item = inventory.getItemByName(itemName);
        if (item != null) {
            items.add(item);
            saveCart();
            items.clear();
        }
    }

    public void removeItem(String itemName) {
        LineBreak();
        ArrayList<String> lines = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(String.format("./src/data/cart_For_%s.csv",
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
            BufferedWriter writer = new BufferedWriter(new FileWriter(String.format("./src/data/cart_For_%s.csv",
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
        System.out.println("\nitem is removed successfully!");
    }

    public void clearCart() {
        items.clear();
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public double getTotalPrice() {
        double total = 0;
        ArrayList<Item> items_in_invtory = loadItemsForUserCSV(user);
        //#TODO : load items from inventory
        for (Item item : items_in_invtory) {
            total += item.getPrice();
        }
        return total;
    }

    public ArrayList<Item> loadItemsForUserCSV(User user) {
        ArrayList<Item> user_items = new ArrayList<Item>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(String.format("./src/data/cart_For_%s.csv",
                user.getUsername() )))) {
                 String line;
                    // scanner.nextLine(); // Skip first line containing column headers
                    while ((line = reader.readLine()) != null) {
                        String[] row = line.split(",");
                        int id = Integer.parseInt(row[0]);
                        String name = row[1];
                        String category = row[2];
                        double price = Double.parseDouble(row[3]);
                        String description = row[4];
                        int loyaltyPoints = Integer.parseInt(row[5]);
                        boolean inStock = Boolean.parseBoolean(row[6]);        
                        Item item = new Item(id, name, category, price, description, loyaltyPoints, inStock);
                        user_items.add(item);
                    }
                }
         catch (IOException e) {
            System.out.println("Error loading items from CSV file: " + e.getMessage());
        }
        return user_items;
        }
    
    public void displayCart(User user) { 
        try (BufferedReader reader = new BufferedReader(new FileReader(String.format("./src/data/cart_For_%s.csv",
        user.getUsername() )));) {
            LineBreak();
            System.out.println("\n-----------------------------------------------Your Cart-----------------------------------------------");
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
    
            System.out.println("+----+----------------+----------------+----------+------------------------+-----------------+--------+\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    // line separator
    public void LineBreak(){
        System.out.println("=====================================================\n");
    }
    

}
