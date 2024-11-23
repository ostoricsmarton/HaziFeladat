package KigyoJatek;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainMenu extends JFrame {
    
    /**
	 * 
	 */
	private static final Logger LOGGER = Logger.getLogger(MainMenu.class.getName());
	private JatekScores jatekScores = new JatekScores(); // JatekScores példány
	private ArrayList<String> players = new ArrayList<>();

    public MainMenu() throws ClassNotFoundException, IOException {
    	
    	jatekScores = jatekScores.readFromFile();
        setTitle("Főmenü");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        for(int i = 0; i < jatekScores.getScores().size(); i++) {
        	players.add(jatekScores.getScores().get(i).getName());
        }
        for (int j = 0; j < jatekScores.getScores().size(); j++) {
            String scoreName = jatekScores.getScores().get(j).getName();

            boolean foundFirstInstance = false; // To track the first occurrence
            for (int l = 0; l < players.size(); l++) {
                if (players.get(l).equalsIgnoreCase(scoreName)) {
                    if (!foundFirstInstance) {
                        // Keep the first instance
                        foundFirstInstance = true;
                    } else {
                        // Remove subsequent duplicates
                        players.remove(l);
                        l--; // Adjust the index due to the removal
                    }
                }
            }
        }
        	

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        add(panel);

        JButton startButton = new JButton("Játék indítása");
        JButton scoreboardButton = new JButton("Scoreboard megtekintése");
        JButton exitButton = new JButton("Kilépés");
        JButton playerButton = new JButton("Játékosok kezelése");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showGameModeSelection();
            }
        });
        
        playerButton.addActionListener(new ActionListener(){
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		showPlayerOptions();
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
                	for (int i = 0; i < players.size(); i++) {
                	    boolean exists = false;
                	    for (int j = 0; j < jatekScores.getScores().size(); j++) {
                	        if (players.get(i).equals(jatekScores.getScores().get(j).getName())) {
                	            exists = true;
                	            break; // Stop searching once we find a match
                	        }
                	    }
                	    if (!exists) {
                	        jatekScores.addPlayerScore(players.get(i), 0);
                	    }
                	}
					jatekScores.writeToFile();
				} catch (IOException e1) {
					LOGGER.log(Level.SEVERE, "Failed to save scores before closing the application", e1);
                    JOptionPane.showMessageDialog(
                        panel,
                        "An error occurred while saving scores. Progress may not be saved.",
                        "Save Error",
                        JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
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
					LOGGER.log(Level.SEVERE, "Failed to save scores before closing the application", e1);
                    JOptionPane.showMessageDialog(
                        panel,
                        "An error occurred while saving scores. Progress may not be saved.",
                        "Save Error",
                        JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} // Save scores before exiting
            }
        });

        panel.add(startButton);
        panel.add(playerButton);
        panel.add(scoreboardButton);
        panel.add(exitButton);
        
    }
    
    private void showNameInput() {
        // Create a dialog for name input
        JDialog nameDialog = new JDialog(this, "Írd be a neved", true);
        nameDialog.setSize(300, 150);
        nameDialog.setLayout(new BorderLayout());
        nameDialog.setLocationRelativeTo(this);

        // Input Panel
        JPanel inputPanel = new JPanel(new BorderLayout());
        JLabel nameLabel = new JLabel("Írd be a neved: ");
        JTextField nameField = new JTextField();

        inputPanel.add(nameLabel, BorderLayout.WEST);
        inputPanel.add(nameField, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        JButton startButton = new JButton("Játék indítása");
        
        

        startButton.addActionListener(e -> {
            String playerName = nameField.getText().trim();
            if (playerName.isEmpty()) {
            	JOptionPane.showMessageDialog(nameDialog, "A név nem lehet üres!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
            	boolean isplayer = false;
            	for(int i = 0; i < players.size(); i++)
            		if(players.get(i).equals(playerName))
            			isplayer = true;
            	if(isplayer) {
                    nameDialog.dispose(); // Close dialog
                    startSinglePlayerGame(playerName);
            	}
            	else {
            		JOptionPane.showMessageDialog(nameDialog, "A játékos nem található!", "Error", JOptionPane.ERROR_MESSAGE);
                }
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

        JOptionPane.showMessageDialog(this, scoresText.toString(), "Toplista", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                MainMenu mainMenu = new MainMenu();
                mainMenu.setVisible(true);
            } catch (ClassNotFoundException | IOException e) {
                LOGGER.log(Level.SEVERE, "Failed to initialize the Main Menu", e);
                showErrorDialog("An error occurred while launching the application.");
            }
        });
    }

    private static void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(null, message, "Hiba", JOptionPane.ERROR_MESSAGE);
    }
    
    public void addPlayer(String name) {
        // Check if the player name already exists
        for (String i : players) {
            if (i.equalsIgnoreCase(name)) {
                JOptionPane.showMessageDialog(null, "A játékos neve már létezik!");
                return ;  // Player name already exists
            }
        }
        // If the name is unique, add the player
        players.add(name);

    }
    
    public void removePlayer(String name) {
        // Use a wrapper to allow modification within the lambda
        final boolean[] playerFound = {false};

        // Remove from scores safely
        jatekScores.getScores().removeIf(player -> {
            if (player.getName().equalsIgnoreCase(name)) {
                playerFound[0] = true;
                return true;
            }
            return false;
        });

        // Remove from players safely
        players.removeIf(playerName -> {
            if (playerName.equalsIgnoreCase(name)) {
                playerFound[0] = true;
                return true;
            }
            return false;
        });

        // Show a message if the player was not found
        if (!playerFound[0]) {
            JOptionPane.showMessageDialog(null, "A játékos nem található!");
        }
    }
    
    public void showPlayerOptions() {
    	String[] options = {"Játékos hozzáadása","Játékosok kiírása", "Játékos törlése"};
        int choice = JOptionPane.showOptionDialog(this, 
                "Válassz módot:", 
                "Játék indítása", 
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.INFORMATION_MESSAGE, 
                null, options, options[0]);

        if (choice == 0) {
        	JDialog nameDialog = new JDialog(this, "Játékos hozzáadása", true);
            nameDialog.setSize(300, 150);
            nameDialog.setLayout(new BorderLayout());
            nameDialog.setLocationRelativeTo(this);

            // Input Panel
            JPanel inputPanel = new JPanel(new BorderLayout());
            JLabel nameLabel = new JLabel("Írd be a neved: ");
            JTextField nameField = new JTextField();

            inputPanel.add(nameLabel, BorderLayout.WEST);
            inputPanel.add(nameField, BorderLayout.CENTER);

            // Button Panel
            JPanel buttonPanel = new JPanel();
            JButton startButton = new JButton("Játékos hozzáadása");
            startButton.addActionListener(e -> {
	            String playerName = nameField.getText().trim();
	            if (playerName.isEmpty()) {
	            	JOptionPane.showMessageDialog(nameDialog, "A név nem lehet üres!", "Error", JOptionPane.ERROR_MESSAGE);
	            }else {
	            	addPlayer(playerName);
	            	nameDialog.dispose();
	            }
            });
            buttonPanel.add(startButton);
            nameDialog.add(inputPanel, BorderLayout.CENTER);
            nameDialog.add(buttonPanel, BorderLayout.SOUTH);
            nameDialog.setVisible(true);
        } else if (choice == 1) {
        	StringBuilder scoresText = new StringBuilder("Játékosok:\n");
            ArrayList<String> names = players;


            for (int i = 0; i < players.size(); i++) {
                scoresText.append(i + 1).append(" :").append(names.get(i)).append("\n");
            }

            JOptionPane.showMessageDialog(this, scoresText.toString(), "Játékosok", JOptionPane.INFORMATION_MESSAGE);
        } else if(choice == 2) {
        	JDialog nameDialog = new JDialog(this, "Játékos törlése", true);
            nameDialog.setSize(300, 150);
            nameDialog.setLayout(new BorderLayout());
            nameDialog.setLocationRelativeTo(this);

            // Input Panel
            JPanel inputPanel = new JPanel(new BorderLayout());
            JLabel nameLabel = new JLabel("Írd be a neved: ");
            JTextField nameField = new JTextField();

            inputPanel.add(nameLabel, BorderLayout.WEST);
            inputPanel.add(nameField, BorderLayout.CENTER);

            // Button Panel
            JPanel buttonPanel = new JPanel();
            JButton startButton = new JButton("Játékos törlése");
            
            startButton.addActionListener(e -> {
	            String playerName = nameField.getText().trim();
	            if (playerName.isEmpty()) {
	            	JOptionPane.showMessageDialog(nameDialog, "A név nem lehet üres!", "Error", JOptionPane.ERROR_MESSAGE);
	            }else {
	            	removePlayer(playerName);
	            	nameDialog.dispose();
	            }
            });
            
            buttonPanel.add(startButton);
            nameDialog.add(inputPanel, BorderLayout.CENTER);
            nameDialog.add(buttonPanel, BorderLayout.SOUTH);
            nameDialog.setVisible(true);
        } 
    }
}