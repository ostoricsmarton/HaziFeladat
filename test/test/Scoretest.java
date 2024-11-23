package test;

import org.junit.Before;
import org.junit.Test;

import KigyoJatek.JatekScores;
import KigyoJatek.PlayerScore;

import static org.junit.Assert.*;

import java.util.ArrayList;

public class Scoretest {

    private JatekScores manager;

    @Before
    public void setUp() {
        manager = new JatekScores();
    }

    @Test
    public void testAddPlayerScore() {
        // Adding a single score
        manager.addPlayerScore("Alice", 100);
        ArrayList<PlayerScore> scores = manager.getScores();

        // Check if the score is added correctly
        assertEquals(1, scores.size());
        assertEquals("Alice", scores.get(0).getName());
        assertEquals(100, scores.get(0).getScore());
    }

    @Test
    public void testSortScoresDescending() {
        // Adding multiple scores
        manager.addPlayerScore("Alice", 100);
        manager.addPlayerScore("Bob", 150);
        manager.addPlayerScore("Charlie", 120);

        ArrayList<PlayerScore> scores = manager.getScores();

        // Check if scores are sorted in descending order
        assertEquals("Bob", scores.get(0).getName()); // Bob should have the highest score
        assertEquals(150, scores.get(0).getScore());
        assertEquals("Charlie", scores.get(1).getName());
        assertEquals(120, scores.get(1).getScore());
        assertEquals("Alice", scores.get(2).getName());
        assertEquals(100, scores.get(2).getScore());
    }

    @Test
    public void testTrimToTop10Scores() {
        // Adding more than 10 scores
        for (int i = 0; i < 15; i++) {
            manager.addPlayerScore("Player" + i, 100 - i);
        }

        ArrayList<PlayerScore> scores = manager.getScores();

        // Check if the list is trimmed to only 10 scores
        assertEquals(10, scores.size()); // Only top 10 scores should be retained
        assertEquals("Player0", scores.get(0).getName()); // The highest score
        assertEquals(100, scores.get(0).getScore());
        assertEquals("Player9", scores.get(9).getName()); // The 10th score
        assertEquals(91, scores.get(9).getScore());

        // Verify that the 11th score is removed (Player 10)
        assertFalse(scores.stream().anyMatch(score -> score.getName().equals("Player10")));
    }

    @Test
    public void testAddPlayerScoreWhenMoreThan10() {
        // Adding exactly 10 scores
        for (int i = 0; i < 10; i++) {
            manager.addPlayerScore("Player" + i, 100 - i);
        }
        // Adding the 11th score
        manager.addPlayerScore("NewPlayer", 200);

        ArrayList<PlayerScore> scores = manager.getScores();

        // Check if the list is trimmed correctly and contains only top 10
        assertEquals(10, scores.size()); // Only 10 scores should be kept
        assertEquals("NewPlayer", scores.get(0).getName()); // New player should have the highest score
        assertEquals(200, scores.get(0).getScore());
        assertFalse(scores.stream().anyMatch(score -> score.getName().equals("Player10"))); // Player10 should be removed
    }
}
