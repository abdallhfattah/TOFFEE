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
                FileWriter writer = new FileWriter("users.csv", true);
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

                return user;
            }
        }
        System.out.println("Invalid username or password!");
        return null;
    }
    public static ArrayList<User> loadUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            Scanner scan = new Scanner(new File("users.csv"));
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
}
