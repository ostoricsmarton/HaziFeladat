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



}
