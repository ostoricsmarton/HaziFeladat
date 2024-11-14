
public class Jatekos {
    private String nev; // A játékos neve
    private JatekScores scores; // A játékos pontszámait tartalmazó JatekScores objektum

    // Konstruktor
    public Jatekos(String nev, JatekScores scores) {
        this.nev = nev;
        this.scores = scores;
    }

    // Getter és setter metódusok
    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public JatekScores getScores() {
        return scores;
    }

    public void setScores(JatekScores scores) {
        this.scores = scores;
    }

    // A játékos pontszámait hozzáadhatjuk a JatekScores példányhoz
    public void addScore(int score) {
        scores.addScore(score);
    }
}