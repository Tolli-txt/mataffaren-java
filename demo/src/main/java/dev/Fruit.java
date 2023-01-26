package dev;

import java.io.Serializable;

// Main fruit class, used in other fruit subclass such as Berries.java
// has the proper setters & getters aswell as implements Serializable which
// makes it possible to serialize the stock of fruits between multiple sessions
abstract class Fruit implements Serializable {
    private String name;
    private double price;
    private int quantity;


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}
