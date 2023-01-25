package dev;

import java.io.*;
import java.util.*;

class FoodStore implements Serializable {
    private ArrayList<Fruit> fruits;
    private Cart cart;
    private Scanner scanner;
    
    public FoodStore() {
        fruits = new ArrayList<Fruit>();
        cart = new Cart();
        scanner = new Scanner(System.in);

        // Adding the fruits for sale
        // fruits.add(new Apple());
        // fruits.add(new Banana());
        // fruits.add(new Pineapple());
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

    public void listFruitsForSale() {
        System.out.println("-------------------");
        System.out.println("- Fruits for sale - ");
        for (Fruit fruit : fruits) {
            System.out.println(fruit.getName() + " - Price: " + fruit.getPrice() + " - Quantity: " + fruit.getQuantity() + "\n");
        }
        System.out.println("-------------------");
    }

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

    private void loadFruits() {
        try {
            FileInputStream fileIn = new FileInputStream("fruits.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            fruits = (ArrayList<Fruit>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            System.out.println("ERROR: IOException in loadFruits");
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("ERROR: Fruit class not found.");
            // c.printStackTrace();
        }
    }

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
            cart.getFruits().clear();
            cart.setTotalPrice(0.0);
            saveFruits();
            System.out.println("Purchase complete!");
        } else {
            System.out.println("Purchase cancelled.");
        }
    }
    
    public void start() {
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