package KigyoJatek;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;

public class MainMenu extends JFrame {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JatekScores jatekScores = new JatekScores(); // JatekScores példány

    public MainMenu() throws ClassNotFoundException, IOException {
    	jatekScores = jatekScores.readFromFile();
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
                try {
					jatekScores.writeToFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} // Save scores before exiting
                System.exit(0);
            }
        });
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
					jatekScores.writeToFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} // Save scores before exiting
            }
        });

        panel.add(startButton);
        panel.add(scoreboardButton);
        panel.add(exitButton);
        
    }
    
    private void showNameInput() {
        // Create a dialog for name input
        JDialog nameDialog = new JDialog(this, "Enter Your Name", true);
        nameDialog.setSize(300, 150);
        nameDialog.setLayout(new BorderLayout());
        nameDialog.setLocationRelativeTo(this);

        // Input Panel
        JPanel inputPanel = new JPanel(new BorderLayout());
        JLabel nameLabel = new JLabel("Enter your name: ");
        JTextField nameField = new JTextField();

        inputPanel.add(nameLabel, BorderLayout.WEST);
        inputPanel.add(nameField, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        JButton startButton = new JButton("Start Game");
        
        

        startButton.addActionListener(e -> {
            String playerName = nameField.getText().trim();
            if (!playerName.isEmpty()) {
                nameDialog.dispose(); // Close dialog
                startSinglePlayerGame(playerName);
            } else {
                JOptionPane.showMessageDialog(nameDialog, "Name cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        buttonPanel.add(startButton);
        


        nameDialog.add(inputPanel, BorderLayout.CENTER);
        nameDialog.add(buttonPanel, BorderLayout.SOUTH);
        nameDialog.setVisible(true);
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
        	showNameInput();
        } else if (choice == 1) {
        	startMultiPlayerGame();
        }
    }

    private void startSinglePlayerGame(String playerName) {
        JFrame gameFrame1 = new JFrame("Snake Game - Egyjátékos mód");
        SnakeJatek gamePanel = new SnakeJatek(gameFrame1, jatekScores,playerName); // Átadjuk a JatekScores példányt

        gameFrame1.add(gamePanel);
        gameFrame1.pack();
        gameFrame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gameFrame1.setVisible(true);
    }
    
    private void startMultiPlayerGame() {
        JFrame gameFrame2 = new JFrame("Snake Game - Többjátékos mód");
        SnakeJatekMulti gamePanel = new SnakeJatekMulti(gameFrame2); // Átadjuk a JatekScores példányt

        gameFrame2.add(gamePanel);
        gameFrame2.pack();
        gameFrame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gameFrame2.setVisible(true);
    }

    private void showScoreboard() {
        StringBuilder scoresText = new StringBuilder("Top 10 pontszám:\n");
        ArrayList<PlayerScore> scores = jatekScores.getScores();


        for (int i = 0; i < Math.min(scores.size(), 10); i++) {
            scoresText.append(i + 1).append(":").append(scores.get(i)).append("\n");
        }

        JOptionPane.showMessageDialog(this, scoresText.toString(), "Scoreboard", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) throws IOException {
        SwingUtilities.invokeLater(() -> {
            MainMenu mainMenu;
			try {
				mainMenu = new MainMenu();
				mainMenu.setVisible(true);
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
        });
        
    }
}