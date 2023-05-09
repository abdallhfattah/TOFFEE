import java.util.Scanner;
import java.io.File;
public class main {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    while (true) {
      System.out.println("What would you like to do?");
      System.out.println("1. Register");
      System.out.println("2. Log in");
      System.out.println("3. Exit");

      int choice = scan.nextInt();

      switch (choice) {
        case 1:
          system.Register();
          break;
        case 2:
          system.login();
          break;
        case 3:
          System.out.println("quitting ...");
          return;
        default:
          System.out.println("Invalid choice. Please try again.");
          break;
      }
    }
  }
}
