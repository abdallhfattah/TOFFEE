import java.util.ArrayList;
import java.io.*;

public class Inventory {
    private ArrayList<Item> itemList;

    public Inventory() {
        itemList = new ArrayList<>();
    }

    public void addItem(Item item) {
        itemList.add(item);
    }

    public Item getItemByName(String itemName) {
        for (Item item : itemList) {
            if (item.getName().equals(itemName)) {
                return item;
            }
        }
        return null; // Item not found
    }

    public void displayItems() {
        System.out.println("+----+----------------+----------------+----------+------------------------+-----------------+--------+");
        System.out.println("| ID |      Name      |    Category    |  Price   |       Description      | Loyalty Points  |In Stock|");
        System.out.println("+----+----------------+----------------+----------+------------------------+-----------------+--------+");

        for (Item item : itemList) {
            System.out.format("| %2d | %-14s | %-14s | $%7.2f | %-22s | %15d | %6s |\n",
                    item.getId(), item.getName(), item.getCategory(), item.getPrice(), item.getDescription(),
                    item.getLoyaltyPoints(), item.isInStock() ? "Yes" : "No");
        }

        System.out.println("+----+----------------+----------------+----------+------------------------+-----------------+--------+");
    }

    public void saveItemsToFile() {
        try {
            FileOutputStream fileOut = new FileOutputStream("Items");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(itemList);
            objectOut.close();
            fileOut.close();
            System.out.println("Items saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving items to file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Create a new inventory
        Inventory inventory = new Inventory();

        // Add some items to the inventory
        Item item1 = new Item(1, "Toffe", "sweets", 29.99, "fluffy candy", 10,true);
        Item item2 = new Item(2, "Molto", "bakery", 9.99, "just molto ", 5,false);
        Item item3 = new Item(2, "cup cake", "bakery", 14.99, "cup cake", 5,true);
        Item item4 = new Item(2, "gums", "bakery", 4.99, "gums", 0,true);
        inventory.addItem(item1);
        inventory.addItem(item2);
        inventory.addItem(item3);
        inventory.addItem(item4);
        inventory.saveItemsToFile();
        // Display the inventory
        inventory.displayItems();
    }

}

// public void loadItemsFromFile() {
//     try {
//         FileInputStream fileIn = new FileInputStream("Items");
//         ObjectInputStream objectIn = new ObjectInputStream(fileIn);
//         itemList = (ArrayList<Item>) objectIn.readObject();
//         objectIn.close();
//         fileIn.close();
//         System.out.println("Items loaded from file.");
//     } catch (IOException | ClassNotFoundException e) {
//         System.out.println("Error loading items from file: " + e.getMessage());
//     }
// }