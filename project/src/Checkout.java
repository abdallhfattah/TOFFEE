import java.util.function.DoubleToIntFunction;

public class Checkout {
    private Cart cart;
    private final double total;
    private boolean is_delivered;

    public Checkout(Cart c) {
        cart = c;
        total = c.getTotalPrice();
        is_delivered = false;
    }

    public void price() {
        System.out.println(total);
    }

    public void status() {
        System.out.println(is_delivered ? "delivered" : "out for delivery!");
    }

    public void payCash(double amount) {
        if (amount == total) {
            System.out.println("here is your order!\nHave a nice day!");
            is_delivered = true;
        }
        else if (amount > total) {
            amount -= total;
            System.out.printf("Here is your change: %.2f\nhere is your order!\nHave a nice day!", amount);
            is_delivered = true;
        }
        else {
            System.out.printf("The Total is: %.2f\nYou only paid: %.2f", total, amount);
        }
    }

    public static void main(String[] args) {
        // Create a new inventory
        Inventory inventory = new Inventory();

        // Add some items to the inventory
        Item item1 = new Item(1, "Toffe", "sweets", 29.99, "fluffy candy", 10,true);
        Item item2 = new Item(2, "Molto", "bakery", 9.99, "just molto ", 5,false);
        Item item3 = new Item(2, "cup cake", "bakery", 14.99, "cup cake", 5,true);
        Item item4 = new Item(2, "gums", "bakery", 4.99, "gums", 0,true);
        inventory.addItem(item1);
        inventory.addItem(item2);
        inventory.addItem(item3);
        inventory.addItem(item4);
        Cart c = new Cart(inventory);
        c.addItem("Toffe");
        c.addItem("Molto");
        c.addItem("gums");
        Checkout check = new Checkout(c);
        check.payCash(44.97);
    }

}
