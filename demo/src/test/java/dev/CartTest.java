package dev;

import org.junit.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CartTest {

    @Test
    public void testAddFruit() {
        Cart cart = new Cart();
        Berries berries = new Berries("Strawberries", 6.49, 50);

        // Adding the fruits to the cart
        String result = cart.addFruit(berries);

        // Expected result should be that it succeeds
        assertEquals("Successfully added to cart", result);
        assertEquals(1, cart.getFruits().size());
        assertEquals(6.49, cart.getTotalPrice());
    }

    @Test
    public void testAddFruitOutOfStock() {
        // Test of buying a fruit that is out-of-stock, so it should fail, i.e. return "This item is out of stock"
        Cart cart = new Cart();
        Berries berries = new Berries("Acai berries", 8.99, 0);

        String result = cart.addFruit(berries);

        assertEquals("This item is out of stock", result);
    }

    @Test
    public void testEmptyCart() {
        // Test to empty the cart, first adding 1 strawberry and then calling the emptyCart()
        // if all goes as intended, the cart should be emptied
        Cart cart = new Cart();
        Berries strawberry = new Berries("Strawberries", 6.49, 10);

        cart.addFruit(strawberry);
        cart.emptyCart();

        assertEquals(0, cart.getFruits().size());
        assertEquals(0.0, cart.getTotalPrice(), 0.01);
    }

    @Test
    public void testDisplayCart() {
        // Tests the displayCart function, first adding some fruits, and then testing the expected output
        Cart cart =  new Cart();
        Berries strawberries = new Berries("Strawberries", 6.49, 50);
        Berries raspberries = new Berries("Raspberries", 5.99, 30);

        cart.addFruit(strawberries);
        cart.addFruit(strawberries);
        cart.addFruit(raspberries);
        cart.addFruit(raspberries);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        cart.displayCart();
        String expectedOutput = "---- Your cart ----" + System.lineSeparator() + 
                "Raspberries x 2 - $11.98" + System.lineSeparator() + 
                "Strawberries x 2 - $12.98" + System.lineSeparator() + 
                "Total price: 24.96" + System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testDisplayCartEmpty() {
        // Testing the displayCart function for when the cart is empty
        Cart cart = new Cart();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        cart.displayCart();

        String expectedOutput = "---- Your cart ----" + System.lineSeparator() +
                "The cart is empty.";

        assertEquals(expectedOutput, outContent.toString().trim());
    }
}
