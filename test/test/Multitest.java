package test;

import org.junit.Before;
import org.junit.Test;

import KigyoJatek.SnakeJatekMulti;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JFrame;

import static org.junit.Assert.*;

public class Multitest {

    private SnakeJatekMulti game;

    @Before
    public void setUp() {
        // Initialize the game with a dummy frame (since we're testing logic, not GUI)
        JFrame dummyFrame = new JFrame();
        game = new SnakeJatekMulti(dummyFrame);

        // Initialize both snakes and place food
        game.setSnake1(new ArrayList<>());
        game.setSnake2(new ArrayList<>());
        game.getSnake1().add(new Point(5, 5));  // Snake1 initial position
        game.getSnake2().add(new Point(8, 8));  // Snake2 initial position
        game.placeFood1();
        game.placeFood2();
        game.setRunning(true);  // Ensure the game is running at the start of each test
        game.setDirection1('R'); // Set initial direction for snake1 (Right)
        game.setDirection2('L'); // Set initial direction for snake2 (Left)
    }

    @Test
    public void testMoveSnake1() {
        // Initial position (5, 5) should be moved to (4, 5) after moving left
        game.setDirection1('L');
        game.moveSnake1();
        assertEquals(new Point(4, 5), game.getSnake1().get(0));

        // Now move it down
        game.setDirection1('D');
        game.moveSnake1();
        assertEquals(new Point(4, 6), game.getSnake1().get(0));
    }

    @Test
    public void testMoveSnake2() {
        // Initial position (8, 8) should be moved to (9, 8) after moving right
        game.setDirection2('R');
        game.moveSnake2();
        assertEquals(new Point(9, 8), game.getSnake2().get(0));

        // Now move it up
        game.setDirection2('U');
        game.moveSnake2();
        assertEquals(new Point(9, 7), game.getSnake2().get(0));
    }

    @Test
    public void testCheckFoodSnake1EatsFood1() {
        // Place food1 at the same position as snake1's head
        game.getSnake1().get(0).setLocation(game.getFood1());

        // Call checkFood and check for snake1 growing
        game.checkFood();

        // Assert that snake1 grew and food1 was replaced
        assertEquals(2, game.getSnake1().size());  // Snake1 should have grown
        assertNotEquals(game.getFood1(), new Point(5, 5));  // Food1 should be replaced
    }

    // Test when snake1 eats food2
    @Test
    public void testCheckFoodSnake1EatsFood2() {
        // Place food2 at the same position as snake1's head
        game.getSnake1().get(0).setLocation(game.getFood2());

        // Call checkFood and check for snake1 growing
        game.checkFood();

        // Assert that snake1 grew and food2 was replaced
        assertEquals(2, game.getSnake1().size());  // Snake1 should have grown
        assertNotEquals(game.getFood2(), new Point(8, 8));  // Food2 should be replaced
    }

    // Test when snake2 eats food1
    @Test
    public void testCheckFoodSnake2EatsFood1() {
        // Place food1 at the same position as snake2's head
        game.getSnake2().get(0).setLocation(game.getFood1());

        // Call checkFood and check for snake2 growing
        game.checkFood();

        // Assert that snake2 grew and food1 was replaced
        assertEquals(2, game.getSnake2().size());  // Snake2 should have grown
        assertNotEquals(game.getFood1(), new Point(5, 5));  // Food1 should be replaced
    }

    // Test when snake2 eats food2
    @Test
    public void testCheckFoodSnake2EatsFood2() {
        // Place food2 at the same position as snake2's head
        game.getSnake2().get(0).setLocation(game.getFood2());

        // Call checkFood and check for snake2 growing
        game.checkFood();

        // Assert that snake2 grew and food2 was replaced
        assertEquals(2, game.getSnake2().size());  // Snake2 should have grown
        assertNotEquals(game.getFood2(), new Point(8, 8));  // Food2 should be replaced
    }

    @Test
    public void testFoodPlacementValidity() {
        // Ensure food is not placed on the snake
        game.setFood1(new Point(6, 6)); // Place food in a valid position
        game.placeFood1(); // Try placing food again
        assertNotEquals(game.getFood1(), game.getSnake1().get(0)); // Food shouldn't overlap snake1

        game.setFood2(new Point(7, 7)); // Place food in a valid position
        game.placeFood2(); // Try placing food again
        assertNotEquals(game.getFood2(), game.getSnake2().get(0)); // Food shouldn't overlap snake2
    }

    @Test
    public void testPlaceFood1() {
        // Test that food is placed at a valid position
        Point initialFood1 = game.getFood1();
        game.placeFood1();
        assertNotEquals(initialFood1, game.getFood1()); // Food should move to a new position
    }

    @Test
    public void testPlaceFood2() {
        // Test that food is placed at a valid position
        Point initialFood2 = game.getFood2();
        game.placeFood2();
        assertNotEquals(initialFood2, game.getFood2()); // Food should move to a new position
    }
}
