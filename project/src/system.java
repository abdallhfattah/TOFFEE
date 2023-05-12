import javax.crypto.spec.OAEPParameterSpec;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
public class system {

    // register manager
    public static void Register() {
        boolean registered = false;
        Scanner scan = new Scanner(System.in);
        System.out.println("(you shouldn't enter spaces or any special character except(_))");
        System.out.print("Enter username : ");
        String username = scan.nextLine();
        System.out.println(
                "Enter password (enter exactly between 8-12 characters at least one uppercase , one digit and one symbol):");
        String password = scan.nextLine();
        System.out.print("Enter address: ");
        String address = scan.nextLine();
        System.out
                .println("Enter phonenumber (enter valid egyptian phonenumber starts with 010 or 011 or 012 or 015):");
        String phonenumber = scan.nextLine();
        System.out.print("Enter gender(male or female): ");
        String gender = scan.nextLine();
        System.out.print("Enter email: ");
        String email = scan.nextLine();
        User newUser = new User(username, password, address, phonenumber, gender, email);
        ArrayList<User> users = loadUsers();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                registered = true;
                System.out.println("UserName already exists!");
                break;
            }
            if (!username.matches("^[a-zA-Z0-9_]*$")) {
                System.out
                        .println("Invalid username (you shouldn't enter spaces or any special character except(_))\n");
                return;
            }
            if (!password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*\\W)[A-Za-z\\d\\W]{8,12}$")) {
                System.out.println(
                        "Invalid password(enter exactly between 8-12 characters at least one uppercase , one digit and one symbol)\n");
                return;
            }
            if (!phonenumber.matches("^01[0-2|5][0-9]{8}$")) {
                System.out.println("Invalid phonenumber(enter valid egyptian phone number)\n");
                return;
            }
            if (!gender.matches("^(male|female)$")) {
                System.out.println("Invalid gender(enter male or female)\n");
                return;
            }
            if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                System.out.println("Invalid email(enter valid email)\n");
                return;
            }
        }
        if (!registered) {
            String otp_message = OTPSender.generateOTP();
            OTPSender otp_sender = new OTPSender();
            otp_sender.sendEmail(email, "Here is your OTP Message: ", otp_message);
            System.out.println("Enter the OTP sent to your mail: ");
            String otp_input = scan.nextLine();
            if (otp_input.equals(otp_message)) {
                try {
                    FileWriter writer = new FileWriter("./src/data/users.csv", true);
                    writer.write(newUser.getUsername() + "," + newUser.getPassword() + "," + newUser.getAddress() + ","
                            + newUser.getPhoneNumber() + "," + newUser.getGender() + "," + newUser.getEmail() + "\n");
                    writer.close();
                    System.out.println("User registered successfully!");
                } catch (IOException e) {
                    System.out.println("Error registering user: " + e.getMessage());
                }
            } else {
                System.out.println("Wrong OTP, Try again!");
            }

        }
    }

    // login manager
    public static User login() {
        LineBreaker();
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scan.nextLine();
        System.out.print("Enter password: ");
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

    public static void LineBreaker(){
        System.out.println("=====================================================");
    }

    // loading user into csv file
    public static ArrayList<User> loadUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            Scanner scan = new Scanner(new File("./src/data/users.csv"));
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

    // displays the users interface 
    public static void DisplayUserInterface(User user){
        Scanner user_choices = new Scanner(System.in);
        Inventory user_inv = new Inventory();
        Cart user_cart = new Cart(user , user_inv);
        while (true) {
          LineBreaker();
          System.out.println("What would you like to do?");
          System.out.println("1. display avaible items");
          System.out.println("2. add to cart");
          System.out.println("3. remove from cart");
          System.out.println("4. display Cart");
          System.out.println("5. checkout");
          System.out.println("6. Logout");
          System.out.print("CHOICE: ");
          int choice = user_choices.nextInt();
          switch (choice) {
            case 1:
                LineBreaker();
                user_inv.displayItems();
                break;
            case 2:
                LineBreaker();
                Scanner scan_add  = new Scanner(System.in);
                System.out.print("what item do you want to add : ");
                String item_name = scan_add.nextLine(); 
                user_cart.addItem(item_name);
                break;
            case 3:
                LineBreaker();
                Scanner scanRemove  = new Scanner(System.in);
                System.out.print("what item do you want to remove : ");
                String itemToRemove = scanRemove.nextLine(); 
                user_cart.removeItem(itemToRemove);
                break;
            case 4:
                user_cart.displayCart(user);
                break;
            case 5:
                LineBreaker();
                Checkout user_Checkout = new Checkout(user_cart, user);
                user_Checkout.checkOut(user);
                break;
            case 6:
              LineBreaker();
              System.out.println(String.format("Good bye %s ", user.getUsername()));
              return;
            default:
              LineBreaker();
              System.out.println("Invalid choice. Please be smarter.");
              break;
            }
        }
    }
}
