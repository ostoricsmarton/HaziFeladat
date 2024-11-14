import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainMenu extends JFrame {
    
    private JatekScores jatekScores = new JatekScores(); // JatekScores példány

    public MainMenu() {
        setTitle("Főmenü");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        add(panel);

        JButton startButton = new JButton("Játék indítása");
        JButton scoreboardButton = new JButton("Scoreboard megtekintése");
        JButton exitButton = new JButton("Kilépés");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showGameModeSelection();
            }
        });

        scoreboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showScoreboard();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        panel.add(startButton);
        panel.add(scoreboardButton);
        panel.add(exitButton);
    }

    private void showGameModeSelection() {
        String[] options = {"Egyjátékos", "Többjátékos"};
        int choice = JOptionPane.showOptionDialog(this, 
                "Válassz módot:", 
                "Játék indítása", 
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.INFORMATION_MESSAGE, 
                null, options, options[0]);

        if (choice == 0) {
            startSinglePlayerGame();
        } else if (choice == 1) {
            JOptionPane.showMessageDialog(this, "Többjátékos mód kiválasztva. Játék indul...");
        }
    }

    private void startSinglePlayerGame() {
        JFrame gameFrame = new JFrame("Snake Game - Egyjátékos mód");
        SnakeJatek gamePanel = new SnakeJatek(gameFrame, jatekScores); // Átadjuk a JatekScores példányt

        gameFrame.add(gamePanel);
        gameFrame.pack();
        gameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gameFrame.setVisible(true);
    }

    private void showScoreboard() {
        StringBuilder scoresText = new StringBuilder("Top 10 pontszám:\n");
        ArrayList<Integer> scores = jatekScores.getScores();

        for (int i = 0; i < Math.min(scores.size(), 10); i++) {
            scoresText.append(i + 1).append(". ").append(scores.get(i)).append("\n");
        }

        JOptionPane.showMessageDialog(this, scoresText.toString(), "Scoreboard", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainMenu mainMenu = new MainMenu();
            mainMenu.setVisible(true);
        });
    }
}