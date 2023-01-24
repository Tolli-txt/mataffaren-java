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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public ArrayList<Fruit> getFruits() {
        return fruits;
    }

    public void displayCart() {
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
        System.out.println("Total price: $" + totalPrice);
    }


    // public void displayCart() {
    //     if (fruits.size() > 0) {
    //         System.out.println("Fruits in cart: ");
    //         for (Fruit fruit : fruits) {
    //             System.out.println(fruit.getName() + " x " + fruit.getQuantity() + " = " + fruit.getPrice() * fruit.getQuantity());
    //         }
    //         System.out.println("Total price: " + totalPrice);
    //     } else {
    //         System.out.println("The cart is empty...");
    //     }
    // }

}
