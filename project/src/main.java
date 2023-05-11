import java.util.Scanner;
public class main {
  public static void main(String[] args) {
    Scanner scan_choice = new Scanner(System.in);
    Inventory n = new Inventory();
    n.displayItems();
    while (true) {
      System.out.println("What would you like to do?");
      System.out.println("1. Register");
      System.out.println("2. Log in");
      System.out.println("3. Exit");
      System.out.print("Enter your choice: ");
      int choice = scan_choice.nextInt();
      switch (choice) {
        case 1:
          system.Register();
          break;
        case 2:
        // #TODO display list of possibilities user can do
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
