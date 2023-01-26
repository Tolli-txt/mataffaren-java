package dev;

import java.util.*;

class Cart {
    private ArrayList<Fruit> fruits;
    private double totalPrice;
    
    // Initializes the ArrayList and set the total price to 0 by default
    public Cart() {
        fruits = new ArrayList<Fruit>();
        totalPrice = 0.0;
    }

    public ArrayList<Fruit> getFruits() {
        return fruits;
    }

    // Function for adding a fruit to the cart
    // When a fruit is added the quantity of the fruit is decreased by 1
    // Finally if the fruit is in stock and is added it can return two different
    // statements depending on the outcome. Success in the form of "Successfully added to cart"
    // or a failure if the 'quantity = 0' in the form of "This item is out of stock"
    public String addFruit(Fruit fruit) {
        int currentQuantity = fruit.getQuantity();
        if (currentQuantity > 0) {
            fruit.setQuantity(currentQuantity - 1);
            fruits.add(fruit);
            totalPrice += fruit.getPrice();
            return "Successfully added to cart";
        } else {
            return "This item is out of stock";
        }
    }

    // Gets the totalPrice from the setTotalPrice function and returns the value
    public double getTotalPrice() {
        return totalPrice;
    }

    // Sets the totalPrice
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    // Empties the cart, first by doing a .clear() of all fruits followed by resetting the totalPrice to 0
    public void emptyCart() {
        fruits.clear();
        totalPrice = 0;
    }

    // Displays the contents of the cart, if the cart is empty then it will return "The cart is empty"
    // If the cart has items in it it uses a HashMap to store the count of each item in it, allowing the
    // function to keep track of the number of each item without having to to loop through the list multiple times.
    // Map.Entry is used to loop through the HashMap to check each item and its price to finally display the totalPrice
    public void displayCart() {
        System.out.println("---- Your cart ----");
        if (fruits.size() == 0) {
            System.out.println("The cart is empty.");
            return;
        }
        HashMap<Fruit, Integer> fruitCount = new HashMap<Fruit, Integer>();
        for (Fruit fruit : fruits) {
            if (fruitCount.containsKey(fruit)) {
                int count = fruitCount.get(fruit);
                fruitCount.put(fruit, count + 1);
            } else {
                fruitCount.put(fruit, 1);
            }
        }
        for (Map.Entry<Fruit, Integer> entry : fruitCount.entrySet()) {
            Fruit fruit = entry.getKey();
            int count = entry.getValue();
            System.out.println(fruit.getName() + " x " + count + " - $" + (fruit.getPrice() * count));
        }
        System.out.println("Total price: " + totalPrice);
    }
}
