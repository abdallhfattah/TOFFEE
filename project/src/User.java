import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class User {
  private String username;
  private String password;


  public User(String username, String password) {
      this.username = username;
      this.password = password;
  }

  public String getUsername() {
      return username;
  }

  public String getPassword() {
      return password;
  }

  // Register a new user by prompting for username and password and adding them to the users CSV file
  public static void register() {
      Scanner scan = new Scanner(System.in);
      System.out.println("Enter username:");
      String username = scan.nextLine();
      System.out.println("Enter password:");
      String password = scan.nextLine();
      User newUser = new User(username, password);
      try {
          FileWriter writer = new FileWriter("users.csv", true);
          writer.write(newUser.getUsername() + "," + newUser.getPassword() + "\n");
          writer.close();
          System.out.println("User registered successfully!");
      } catch (IOException e) {
          System.out.println("Error registering user: " + e.getMessage());
      }
      scan.close();
  }

  // Prompt the user to login and check the users CSV file to see if the entered credentials match any existing user
  public static User login() {
      Scanner scan = new Scanner(System.in);
      System.out.println("Enter username:");
      String username = scan.nextLine();
      System.out.println("Enter password:");
      String password = scan.nextLine();
      ArrayList<User> users = loadUsers();
      for (User user : users) {
          if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
              System.out.println("Login successful!");
              scan.close();
              return user;
          }
      }
      System.out.println("Invalid username or password!");
      scan.close();
      return null;
  }
  

  // Load the list of users from the users CSV file
  private static ArrayList<User> loadUsers() {
      ArrayList<User> users = new ArrayList<>();
      try {
          Scanner scan = new Scanner(new File("users.csv"));
          while (scan.hasNextLine()) {
              String line = scan.nextLine();
              String[] fields = line.split(",");
              if (fields.length == 2) {
                  User user = new User(fields[0], fields[1]);
                  users.add(user);
              }
          }
          scan.close();
      } catch (FileNotFoundException e) {
          System.out.println("Error loading users: " + e.getMessage());
      }
      return users;
  }
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    System.out.println("Welcome to the program!");

    while (true) {
        System.out.println("What would you like to do?");
        System.out.println("1. Register");
        System.out.println("2. Log in");
        System.out.println("3. Exit");

        int choice = scan.nextInt();

        switch (choice) {
            case 1:
              register();
            break;
            case 2:
                login();
                break;
            case 3:
                System.out.println("Goodbye!");
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }
  }

}