import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class User {
  private String UserName;
  private String Password;
  private String Address;
  private String PhoneNumber;
  private String Email;
  private String Gender;

  User() {
  }

  public User(String username, String password) {
    this.UserName = username;
    this.Password = password;
  }

  public String getAddress() {
    return Address;
  }

  public void setAddress(String Address) {
    this.Address = Address;
  }

  public String getPhoneNumber() {
    return PhoneNumber;
  }

  public void setPhoneNumber(String PhoneNumber) {
    this.PhoneNumber = PhoneNumber;
  }

  public String getEmail() {
    return Email;
  }

  public void setEmail(String Email) {
    this.Email = Email;
  }

  public String getGender() {
    return Gender;
  }

  public void setGender(String Gender) {
    this.Gender = Gender;
  }

  public String getUsername() {
    return UserName;
  }

  public String getPassword() {
    return Password;
  }

  public static ArrayList<User> loadUsers() {
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

    } catch (FileNotFoundException e) {
      System.out.println("Error loading users: " + e.getMessage());
    }
    return users;
  }

}