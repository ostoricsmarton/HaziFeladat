package KigyoJatek;

import java.io.*;
import java.util.ArrayList;

public class JatekScores implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String FILE_PATH = ".\\resources\\Top10.txt";

    private ArrayList<PlayerScore> scores;

    public JatekScores() {
        scores = new ArrayList<>();
    }

    public void addPlayerScore(String name, int score) {
    	scores.add(new PlayerScore(name, score));
    	scores.sort((a, b) -> Integer.compare(b.getScore(), a.getScore())); // Sort descending

    	if (scores.size() > 10) {
    	    // Check if the lowest score (at index size - 1) is a duplicate of any other entry
    	    PlayerScore lastScore = scores.get(scores.size() - 1);
    	    boolean isUnique = scores.stream()
    	                             .filter(scoreEntry -> scoreEntry.getName().equals(lastScore.getName()))
    	                             .count() == 1;

    	    if (!isUnique) {
    	        scores.remove(scores.size() - 1); // Remove the last score if it's a duplicate
    	    }
    	}
        System.out.println("Scores after adding: " + scores);
    }

    public ArrayList<PlayerScore> getScores() {
        return scores;
    }


    public  JatekScores readFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (JatekScores) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading scores from file: " + e.getMessage());
            return new JatekScores(); // Return a new instance if reading fails
        }
    }

    public void writeToFile() throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            System.out.println("Saving scores: " + scores); // Debug statement
            out.writeObject(this);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Top 10 Scores:\n");
        for (int i = 0; i < scores.size(); i++) {
            sb.append((i + 1)).append(". ").append(scores.get(i)).append("\n");
        }
        return sb.toString();
    }
}