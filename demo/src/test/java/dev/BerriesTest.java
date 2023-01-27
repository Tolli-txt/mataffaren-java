package dev;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BerriesTest {
    
    @Test
    public void testStrawberries() {
        Berries berries = new Berries("Strawberries", 6.49, 30);
        assertEquals("Strawberries", berries.getName());
        assertEquals(6.49, berries.getPrice(), 0.01);
        assertEquals(30, berries.getQuantity());
    }

    @Test
    public void testBlueberries() {
        Berries berries = new Berries("Blueberries", 5.49, 50);
        assertEquals("Blueberries", berries.getName());
        assertEquals(5.49, berries.getPrice(), 0.01);
        assertEquals(50, berries.getQuantity());
    }

    @Test
    public void testSUPERBERRY() {
        Berries berries = new Berries("SUPER EXTREME BERRY: ULTRA LIMITED EDITION", 1337, 1);
        assertEquals("SUPER EXTREME BERRY: ULTRA LIMITED EDITION", berries.getName());
        assertEquals(1337, berries.getPrice(), 0.01);
        assertEquals(1, berries.getQuantity());
    }

    @Test
    public void testFailedBerry() {
        // Checking to see what happens if I don't put the price as a float, i.e. 6 instead of 6.0
        Berries berries = new Berries("Physalis", 6, 5);
        assertEquals("Physalis", berries.getName());
        assertEquals(6, berries.getPrice(), 0.01);
        assertEquals(5, berries.getQuantity());
    }
}
