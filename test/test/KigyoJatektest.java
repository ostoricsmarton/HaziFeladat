package test;

import org.junit.Before;
import org.junit.Test;

import KigyoJatek.JatekScores;
import KigyoJatek.SnakeJatek;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class KigyoJatektest {

    private SnakeJatek game;

    @Before
    public void setUp() {
        // Mock constructor parameters
        JFrame dummyFrame = new JFrame();
        JatekScores dummyScores = new JatekScores();
        String dummyPlayerName = "TestPlayer";

        // Instantiate the SnakeJatek game
        game = new SnakeJatek(dummyFrame, dummyScores, dummyPlayerName);
        game.setBoardDimensions(10, 10); // Example BOARD_WIDTH = 10, BOARD_HEIGHT = 10

        // Initialize the snake using the setter
        ArrayList<Point> initialSnake = new ArrayList<>();
        initialSnake.add(new Point(5, 5)); // Head
        initialSnake.add(new Point(5, 6)); // Body
        initialSnake.add(new Point(5, 7)); // Tail
        game.setSnake(initialSnake); // Using setter

        // Set initial direction
        game.setDirection('U'); // 'U' for Up
    }

    @Test
    public void testMoveSnake_SequentialDirections() {
        // Initial direction is Up
        game.moveSnake();
        List<Point> snake = game.getSnake(); // Using getter
        assertEquals(new Point(5, 4), snake.get(0)); // New head after moving up
        assertEquals(new Point(5, 5), snake.get(1)); // Previous head -> now body
        assertEquals(new Point(5, 6), snake.get(2)); // Body -> now tail
        assertEquals(3, snake.size()); // Snake size should remain the same

        // Change direction to Right
        game.setDirection('R'); // 'R' for Right
        game.moveSnake();
        snake = game.getSnake(); // Using getter
        assertEquals(new Point(6, 4), snake.get(0)); // New head after moving right
        assertEquals(new Point(5, 4), snake.get(1)); // Previous head -> now body
        assertEquals(new Point(5, 5), snake.get(2)); // Body -> now tail
        assertEquals(3, snake.size()); // Snake size should remain the same

        // Change direction to Down
        game.setDirection('D'); // 'D' for Down
        game.moveSnake();
        snake = game.getSnake(); // Using getter
        assertEquals(new Point(6, 5), snake.get(0)); // New head after moving down
        assertEquals(new Point(6, 4), snake.get(1)); // Previous head -> now body
        assertEquals(new Point(5, 4), snake.get(2)); // Body -> now tail
        assertEquals(3, snake.size()); // Snake size should remain the same

        // Change direction to Left
        game.setDirection('L'); // 'L' for Left
        game.moveSnake();
        snake = game.getSnake(); // Using getter
        assertEquals(new Point(5, 5), snake.get(0)); // New head after moving left
        assertEquals(new Point(6, 5), snake.get(1)); // Previous head -> now body
        assertEquals(new Point(6, 4), snake.get(2)); // Body -> now tail
        assertEquals(3, snake.size()); // Snake size should remain the same
    }
    @Test
    public void testCheckFood_EatsFood() {
        // Set food at the head's position
        Point foodPosition = new Point(5, 5);
        game.setFood(foodPosition); // Assuming a setter for food

        // Set initial score (assuming a getter for score)
        int initialScore = game.getScore();

        // Before checkFood(), snake hasn't eaten
        assertEquals(initialScore, game.getScore());

        // Call checkFood() to check if the snake eats the food
        game.checkFood();

        // After eating, score should increase by 10
        assertEquals(initialScore + 10, game.getScore());

        // Snake should grow by 1 segment
        assertEquals(4, game.getSnake().size()); // 3 initial + 1 new segment

        // Food should be relocated
        assertNotEquals(foodPosition, game.getFood()); // Food should have moved
    }

    @Test
    public void testCheckFood_DoesNotEatFood() {
        // Set food at a different position than the snake's head
        Point foodPosition = new Point(10, 10);
        game.setFood(foodPosition); // Assuming a setter for food

        // Set initial score
        int initialScore = game.getScore();

        // Call checkFood(), snake should not eat food as it's not at the head's position
        game.checkFood();

        // Score should not change
        assertEquals(initialScore, game.getScore());

        // Snake's size should remain the same
        assertEquals(3, game.getSnake().size());
    }

    @Test
    public void testSnakeSizeAfterFood() {
        // Set initial food at snake's head position
        game.setFood(new Point(5, 5)); // Assuming a setter for food

        // Initial snake size
        int initialSize = game.getSnake().size();

        // Call checkFood(), the snake should eat food and grow
        game.checkFood();

        // Assert the snake has grown
        assertEquals(initialSize + 1, game.getSnake().size());
    }
    @Test
    public void testPlaceFood_ValidPosition1() {
        // Directly call placeFood() to test food placement
        game.placeFood();

        // Get the new food position
        Point newFood = game.getFood(); // Assuming a getter for food

        // Food should not spawn on the snake
        for (Point part : game.getSnake()) {
            assertNotEquals(part, newFood); // Ensure food is not on any part of the snake
        }

        // Ensure food is within board bounds
        assertTrue(newFood.x >= 0 && newFood.x < game.getBoardWidth());
        assertTrue(newFood.y >= 0 && newFood.y < game.getBoardHeight());
    }

    @Test
    public void testFoodPlacementAfterEating() {
        // Set food at snake's head position
        Point foodPosition = new Point(5, 5);
        game.setFood(foodPosition); // Assuming a setter for food

        // Simulate snake eating the food by directly calling placeFood()
        game.placeFood();

        // Get the new food position after eating
        Point newFood = game.getFood();

        // Food should be relocated after the snake eats it
        assertNotEquals(foodPosition, newFood);

        // Food should not spawn on the snake
        for (Point part : game.getSnake()) {
            assertNotEquals(part, newFood);
        }

        // Ensure food is within the board bounds
        assertTrue(newFood.x >= 0 && newFood.x < game.getBoardWidth());
        assertTrue(newFood.y >= 0 && newFood.y < game.getBoardHeight());
    }
    
    @Test
    public void testCheckCollision_HeadOutOfBounds() {
        // Set the snake's head to be out of bounds (e.g., at x = -1)
        game.getSnake().get(0).setLocation(-1, 5); // Head at (-1, 5)

        // Check collision
        game.checkCollision();

        // Assert that the game is not running after collision
        assertFalse(game.isRunning()); // Assuming `isRunning()` is a getter for `running`
    }

    @Test
    public void testCheckCollision_HeadCollidesWithBody() {
        // Set the snake's head to collide with its body (head at position (5, 6))
        game.getSnake().get(0).setLocation(5, 6); // Head at (5, 6)

        // Check collision
        game.checkCollision();

        // Assert that the game is not running after collision
        assertFalse(game.isRunning());
    }

    @Test
    public void testCheckCollision_NoCollision() {
        // Move the snake's head to a valid position where there's no collision
        game.getSnake().get(0).setLocation(5, 5); // Head at (5, 5)

        // Check collision
        game.checkCollision();

        // Assert that the game is still running (no collision)
        assertTrue(game.isRunning());
    }
}
