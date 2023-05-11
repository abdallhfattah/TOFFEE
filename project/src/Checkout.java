import java.util.Scanner;
import java.io.*;

public class Checkout {
    private Cart cart;
    private final double total;
    private User cart_user;
    private boolean is_delivered;

    public Checkout(Cart c  , User user) {
        cart = c;
        cart_user = user;
        total = c.getTotalPrice();
        is_delivered = false;
    }

    public void price() {
        System.out.println(total);
    }

    public void status() {
        System.out.println(is_delivered ? "delivered" : "out for delivery!");
    }

    // paying cash
    public boolean payCash(double amount) {
        is_delivered = false;
        if (amount == total) {
            System.out.println("here is your order!\nHave a nice day!");
            is_delivered = true;
        }
        else if (amount > total) {
            amount -= total;
            System.out.printf("Here is your change: %.2f\nhere is your order!\nHave a nice day!\n", amount);
            is_delivered = true;
        }
        else {
            System.out.printf("The Total is: %.2f\nYou only paid: %.2f\n", total, amount);
        }
        return is_delivered;
    }
    // check out management
    public void checkOut(User user){
        Scanner scan = new Scanner(System.in);
        while (true) {
            LineBreaker();
            System.out.println("1-Payment info\n2-PayCash\n3-exit");
            System.out.print("CHOICE: ");
            int choice = scan.nextInt();
        switch (choice) {
            case 1:
                LineBreaker();
                System.out.println("Here is the your info!");
                System.out.println(String.format("shipping address %s: ", user.getAddress()));
                System.out.println(String.format("Amount to Pay: %.2f $", total));
                System.out.println(String.format("Approximate time to delivery: %s ", estimateDeliveryTime(total)));
                break;
            case 2:
                LineBreaker();
                System.out.print("Enter the amount to pay: ");
                double amount = scan.nextDouble();
                payCash(amount);
                if(is_delivered){
                    flushCart();
                    System.out.println("Thank you for shopping with us!");
                }
                return;
            case 3:
                return;
            default:
                System.out.println("Invalid choice. Please be smarter.");
                break;
        }
    }
    }
    // estimated time to deliver
    public static String estimateDeliveryTime(double amountPaid) {
        double deliveryTimeInDays = 5 - (amountPaid / 1000);
        if (deliveryTimeInDays < 2) {
            deliveryTimeInDays = 2;
        } else if (deliveryTimeInDays > 5) {
            deliveryTimeInDays = 5;
        }
    
        int days = (int) deliveryTimeInDays;
        int hours = (int) ((deliveryTimeInDays - days) * 24);
    
        if (hours > 0) {
            return days + " days, " + hours + " hours";
        } else {
            return days + " days";
        }
    }

    // deleting items from cart
    public void flushCart(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(String.format("./data/cart_For_%s.csv",
        cart_user.getUsername()), false));){
                writer.write(""); // flush the cart
                writer.close();
        } catch (IOException e) {
            System.out.println("Operation Failed please try again! \n");
            e.printStackTrace();
        }
    }

    public static void LineBreaker(){
        System.out.println("=====================================================");
    }

}
    