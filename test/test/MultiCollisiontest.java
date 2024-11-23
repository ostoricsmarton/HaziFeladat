package test;

import org.junit.Before;
import org.junit.Test;

import KigyoJatek.SnakeJatekMulti;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JFrame;

import static org.junit.Assert.*;

public class MultiCollisiontest {

    private SnakeJatekMulti game;

    // Runs before each test method to set up the game state
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

    // Test when snake1 goes out of bounds
    @Test
    public void testCheckCollisionOutOfBounds() {
        // Move snake1 out of bounds
        game.getSnake1().get(0).setLocation(0, 0);  // Place snake1 head at (0, 0)
        game.setDirection1('L');  // Move snake1 left (out of bounds)
        game.moveSnake1();  // Perform the move
        game.checkCollision();  // Check for collisions

        // Assert that the game is no longer running (since it went out of bounds)
        assertFalse(game.isRunning());
    }

    // Test self-collision of snake1
    @Test
    public void testCheckCollisionSelfCollision() {
        // Add parts to snake1 to create a body
        game.getSnake1().add(new Point(4, 5));  // Add another part of snake1
        game.getSnake1().add(new Point(3, 5));  // Add another part of snake1
        game.getSnake1().get(0).setLocation(5, 5);  // Head at the same position as part
        game.setDirection1('L');  // Move snake1 left into its own body

        // Perform the move and update the state
        game.moveSnake1();  // Move snake1
        game.checkCollision();  // Check for self-collision

        // Assert that the game is no longer running (since snake1 collided with itself)
        assertFalse(game.isRunning());
    }

    // Test collision between snake1 and snake2
    @Test
    public void testCheckCollisionSnakeToSnake() {
        // Place a part of snake2 at the same position where snake1 will move
        game.getSnake2().add(new Point(4, 5));  // Place a part of snake2
        game.getSnake1().get(0).setLocation(5, 5);  // Snake1's head at (5, 5)
        game.setDirection1('L');  // Move snake1 left to collide with snake2

        // Perform the move and update the state
        game.moveSnake1();  // Move snake1 to (4, 5), where snake2's part is located
        game.checkCollision();  // Check for snake-to-snake collision

        // Assert that the game is no longer running (since snake1 collided with snake2)
        assertFalse(game.isRunning());
    }
}