package dev;

import static org.junit.Assert.*;
import org.junit.*;
import java.io.*;
import java.util.*;

// import org.junit.Test;

public class FoodStoreTest {

    @Test
    public void testListFruitsForSale() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        FoodStore foodStore = new FoodStore();
        foodStore.fruits.add(new Berries("Strawberries", 6.49, 50));
        foodStore.fruits.add(new Berries("Raspberries", 5.99, 30));
        foodStore.listFruitsForSale();
        String expectedOutput = "- Fruits for sale -" + System.lineSeparator() + 
                "Strawberries - Price: 6.49 - Quantity: 50" + System.lineSeparator() +
                "Raspberries - Price: 5.99 - Quantity: 30" + System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString());
    }

}
