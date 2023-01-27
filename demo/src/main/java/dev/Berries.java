package dev;

class Berries extends Fruit {
    // Extends the fruit class, variables name, price, quantity are used
    // so that you can make multiple berries without needing to make more classes
    // in other words, only one class is needed when doing it like this
    public Berries(String name, double price, int quantity) {
        setName(name);
        setPrice(price);
        setQuantity(quantity);
    }    
}