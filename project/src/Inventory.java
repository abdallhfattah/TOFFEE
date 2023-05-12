import java.util.ArrayList;
import java.io.*;
import java.util.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Inventory {
    private ArrayList<Item> itemList;

    public Inventory() {
        itemList = new ArrayList<>();
    }

    public void addItem(Item item) {
        itemList.add(item);
    }

    public Item getItemByName(String itemName) {    
        loadItemsFromCSV();
        for (Item item : itemList) {
            if (item.getName().equals(itemName)) {
                return item;
            }
        }
        return null; // Item not found
    }

    public void displayItems() {
        // using d compatable with windows and linux users

        try (BufferedReader reader = new BufferedReader(new FileReader("./src/data/items.csv"));) {
            reader.readLine(); // Skip first line containing column headers
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

    // save the data in the CSV file
    public void saveItemsToCSV() {
        try {
            // Read existing items from CSV file and store them in a HashSet
            Set<String> existingItems = new HashSet<>();
            File file = new File("./src/data", "items.csv");
            if (file.exists()) {
                try (Scanner scanner = new Scanner(new File(file.getPath()))) {
                    scanner.nextLine(); // Skip first line containing column headers
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        Scanner lineScanner = new Scanner(line);
                        lineScanner.useDelimiter(",");
                        int id = lineScanner.nextInt();
                        String name = lineScanner.next();
                        existingItems.add(name); // Add item name to set
                    }
                }
            }
            // Write new items to CSV file
            FileWriter writer = new FileWriter(file.getPath(), true);
            int itemsAdded = 0;
            if (file.length() == 0) {
                writer.write("ID,Name,Category,Price,Description,Loyalty Points,In Stock\n");
            }
            for (Item item : itemList) {
                if (existingItems.contains(item.getName())) {
                    System.out.println("Item '" + item.getName() + "' already exists in the CSV file.");
                    continue;
                }
                writer.write(item.getId() + "," + item.getName() + "," + item.getCategory() + "," +
                        item.getPrice() + "," + item.getDescription() + "," + item.getLoyaltyPoints() + "," +
                        item.isInStock() + "\n");
                itemsAdded++;
                System.out.println("Item '" + item.getName() + "' added to CSV file.");
            }
            writer.close();
            System.out.println(itemsAdded + " items added to CSV file.");
        } catch (IOException e) {
            System.out.println("Error saving items to CSV file: " + e.getMessage());
        }
    }

    public ArrayList<Item> loadItemsFromCSV() {
    try {
        File file = new File("./src/data", "items.csv");
        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                scanner.nextLine(); // Skip first line containing column headers
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    Scanner lineScanner = new Scanner(line);
                    lineScanner.useDelimiter(",");
                    int id = lineScanner.nextInt();
                    String name = lineScanner.next();
                    String category = lineScanner.next();
                    double price = lineScanner.nextDouble();
                    String description = lineScanner.next();
                    int loyaltyPoints = lineScanner.nextInt();
                    boolean inStock = lineScanner.nextBoolean();
                    Item item = new Item(id, name, category, price, description, loyaltyPoints, inStock);
                    itemList.add(item);
                }
            }
        }
    } catch (IOException e) {
        System.out.println("Error loading items from CSV file: " + e.getMessage());
    }
    return itemList;
    }

    
    public static void main(String[] args) {
        // Create a new inventory
        Inventory inventory = new Inventory();
        // System.out.println(Paths.get(System.getProperty("user.dir"), "Desktop", "soft-assignment", "TOFFEE", "project", "src", "data"));    
        // Add some items to the inventory
        Item item1 = new Item(1, "Toffe", "sweets", 29.99, "fluffy candy", 10,true);
        Item item2 = new Item(2, "Moltossss", "bakery", 9.99, "just molto ", 5,false);
        Item item3 = new Item(2, "cup cake", "bakery", 14.99, "cup cake", 5,true);
        Item item4 = new Item(2, "bomba", "bakery", 4.99, "gums", 0,true);
        inventory.addItem(item1);
        inventory.addItem(item2);
        inventory.addItem(item3);
        inventory.addItem(item4);
        inventory.saveItemsToCSV();
        // Display the inventory
        inventory.displayItems();
    }

}
