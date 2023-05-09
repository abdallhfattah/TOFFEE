import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
public class system extends User {
    public static void Register() {
        boolean registered = false;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter username:");
        String username = scan.nextLine();
        System.out.println("Enter password:");
        String password = scan.nextLine();
        User newUser = new User(username, password);
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
                FileWriter writer = new FileWriter("bin/users.csv", true);
                writer.write(newUser.getUsername() + "," + newUser.getPassword() + "\n");
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
}
