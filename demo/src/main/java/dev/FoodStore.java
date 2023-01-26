package dev;

import java.io.*;
import java.util.*;

class FoodStore implements Serializable {
    public ArrayList<Fruit> fruits;
    private Cart cart;
    private Scanner scanner;
    
    // FoodStore constructor
    public FoodStore() {
        fruits = new ArrayList<Fruit>();
        cart = new Cart();
        scanner = new Scanner(System.in);
    }

    // fetches the 'private Cart' so it can be used in other files
    public Cart getCart() {
        return this.cart;
    }

    // fetches the 'private Scanner' so it can be used in other files
    public Scanner getScanner() {
        return this.scanner;
    }

    // Function for creating the initial assortment of fruits
    // only works if there is no 'fruits.ser'-file in the project
    private void createFruits() {
        fruits.add(new Berries("Strawberries", 6.49, 50));
        fruits.add(new Berries("Raspberries", 5.99, 30));
        fruits.add(new Berries("Blackberries", 5.99, 35));
        fruits.add(new Berries("Blueberries", 4.99, 60));
        fruits.add(new Berries("Redcurrants", 4.99, 30));
        fruits.add(new Berries("Blackcurrants", 5.49, 20));
        fruits.add(new Berries("Cranberries", 4.49, 60));
        fruits.add(new Berries("Acai berries", 8.99, 30));
        fruits.add(new Berries("Mulberries", 6.99, 15));
        fruits.add(new Berries("Physalis", 7.49, 20));
        fruits.add(new Berries("SUPER EXTREME BERRY: ULTRA LIMITED EDITION", 1337, 1));
    }

    // Displays all fruits for sale through a for-loop
    public void listFruitsForSale() {
        System.out.println("- Fruits for sale -");
        for (Fruit fruit : fruits) {
            System.out.println(fruit.getName() + " - Price: " + fruit.getPrice() + " - Quantity: " + fruit.getQuantity());
        }
    }

    // Function for adding items to cart, first displays all fruits for sale through listFruitsForSale()
    // then prompts user to input which fruit they want, scans the input and if it matches
    // a fruit for sale, it then adds the fruit, and it wrong input is given, then failure
    public void addToCart() {
        listFruitsForSale();
        System.out.println("Enter the name of the fruit you want to add to the cart: ");
        String name = scanner.nextLine();
        for (Fruit fruit : fruits) {
            if (fruit.getName().equalsIgnoreCase(name)) {
                String result = cart.addFruit(fruit);
                System.out.println(result);
                return;
            }
        }
        System.out.println("Fruit not found...");
    }
    
    // Serializes the fruits into a .ser-file, in this case 'fruits.ser'
    // This is step 1 of a 2-step process to make the inventory persist through restarts
    private void saveFruits() {
        try {
            FileOutputStream fileOut = new FileOutputStream("fruits.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(fruits);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            System.out.println("ERROR: IOException in saveFruits");
            // i.printStackTrace();
        }
    }

    // Step 2; Loads in and deserializes the 'fruits.ser' file and makes it available for usage
    // in the foodStore, i.e. the stock from the previous session has persisted
    private void loadFruits() {
        try {
            FileInputStream fileIn = new FileInputStream("fruits.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            fruits = (ArrayList<Fruit>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            System.out.println("ERROR: IOException in loadFruits");
            // i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("ERROR: Fruit class not found.");
            // c.printStackTrace();
        }
    }

    // Function to complete purchase, it uses scanner to read user input and then
    // executes the proper code based on which input was given.
    // If the cart is empty then it will return "The cart is empty..."
    // If the cart has items in it the user is asked if they want to complete the purchase (y/n)
    // if they select 'y' then the purchase will be completed, which then clears the cart of all fruits
    // and resets the totalPrice back to 0.0. If the user selects 'n' then the purchase completion is cancelled
    // the user can then add more fruits to the cart or just simply relaunch the completion process
    public void completePurchase() {
        double totalPrice = cart.getTotalPrice();
        if (totalPrice == 0) {
            System.out.println("The cart is empty...");
            return;
        }
        System.out.println("Total price: " + totalPrice);
    
        System.out.print("Do you want to complete the purchase? (y/n): ");
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("y")) {
            this.cart.getFruits().clear();
            this.cart.setTotalPrice(0.0);
            saveFruits();
            System.out.println("Purchase complete!");
        } else {
            System.out.println("Purchase cancelled.");
        }
    }
    
    // Function that acts as the main menu, filled with options and switch cases that do
    // different things depending on user input, also initializes createFruits() and loadFruits()
    // at the start to either create an assortment of fruits if there is none, or to just load in the
    // previous assortment from a previous session through loadFruits()
    public void start() {
        createFruits();
        loadFruits();
        while (true) {
            System.out.println("\nWelcome to the fruit store.");
            System.out.println("1. List fruits for sale");
            System.out.println("2. Add to cart");
            System.out.println("3. View cart");
            System.out.println("4. Complete purchase");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    listFruitsForSale();
                    break;
                case 2:
                    addToCart();
                    break;
                case 3:
                    cart.displayCart();
                    break;
                case 4:
                    completePurchase();
                    break;
                case 5:
                    System.out.println("Thanks for shopping with us!");
                    return;
                default:
                    System.out.println("Invalid choice, try again!");
                    break;
            }
        }
    }
    
    public static void main(String[] args) {
        FoodStore foodStore = new FoodStore();
        foodStore.start();
    }
}