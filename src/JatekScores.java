import java.util.ArrayList;

public class JatekScores {
    private ArrayList<Integer> scores;

    public JatekScores() {
        scores = new ArrayList<>();
    }

    public void addScore(int score) {
        scores.add(score);
    }

    public ArrayList<Integer> getScores() {
        return scores;
    }
}