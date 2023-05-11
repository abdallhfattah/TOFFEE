import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
public class system {
    public static void Register() {
        boolean registered = false;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter username:");
        String username = scan.nextLine();
        System.out.println("Enter password:");
        String password = scan.nextLine();
        System.out.println("Enter address:");
        String address = scan.nextLine();
        System.out.println("Enter phonenumber:");
        String phonenumber = scan.nextLine();
        System.out.println("Enter gender:");
        String gender = scan.nextLine();
        System.out.println("Enter email:");
        String email = scan.nextLine();
        User newUser = new User(username, password, address, phonenumber, gender, email);
        ArrayList<User> users = loadUsers();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                registered = true;
                System.out.println("UserName already exists!");
                break;
            }
        }
        if (!registered) {
            try {
                FileWriter writer = new FileWriter("./data/users.csv", true);
                writer.write(newUser.getUsername() + "," + newUser.getPassword() + "," + newUser.getAddress() + ","
                        + newUser.getPhoneNumber() + "," + newUser.getGender() + "," + newUser.getEmail() + "\n");
                writer.close();
                System.out.println("User registered successfully!");
            } catch (IOException e) {
                System.out.println("Error registering user: " + e.getMessage());
            }
        }
    }

    public static User login() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter username:");
        String username = scan.nextLine();
        System.out.println("Enter password:");
        String password = scan.nextLine();
        ArrayList<User> users = loadUsers();
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                System.out.println("welcome back " + user.getUsername() + " (^V^)");
                DisplayUserInterface(user);
                return user;
            }
        }
        System.out.println("Invalid username or password!");
        return null;
    }

    public static ArrayList<User> loadUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            Scanner scan = new Scanner(new File("data/users.csv"));
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] fields = line.split(",");
                if (fields.length == 6) {
                    User user = new User(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5]);
                    users.add(user);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
        return users;
    }

    public static void DisplayUserInterface(User user){
        Scanner user_choices = new Scanner(System.in);
        Inventory user_inv = new Inventory();
        Cart user_cart = new Cart( user , user_inv);
        while (true) {
          System.out.println("What would you like to do?");
          System.out.println("1. display avaible items");
          System.out.println("2. add to cart");
          System.out.println("3. remove from cart");
          System.out.println("4. display Cart");
          System.out.println("5. Logout");
          int choice = user_choices.nextInt();
          switch (choice) {
            case 1:
                user_inv.displayItems();
                break;
            case 2:
                Scanner scan_add  = new Scanner(System.in);
                System.out.print("what item do you want : ");
                String item_name = scan_add.nextLine(); 
                user_cart.addItem(item_name);
                break;
            case 3:
                Scanner scanRemove  = new Scanner(System.in);
                System.out.print("what item do you want : ");
                String itemToRemove = scanRemove.nextLine(); 
                user_cart.removeItem(itemToRemove);
                break;
            case 4:
                user_cart.displayCart(user);
                break;
            case 5:
              System.out.println("Logout");
              return;
            default:
              System.out.println("Invalid choice. Please be smarter.");
              break;
            }
        }
    }
}
