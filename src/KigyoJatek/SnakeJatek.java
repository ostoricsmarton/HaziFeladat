package KigyoJatek;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class SnakeJatek extends JPanel implements ActionListener {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int TILE_SIZE = 40;
    private int BOARD_WIDTH = 20;
    private int BOARD_HEIGHT = 20;
    
    private Timer timer;
    private ArrayList<Point> snake;
    private String playername;
    private Point food;
    private char direction = 'R';
    private boolean running = true;
    private JFrame gameFrame;
    private int score = 0; // Pontszám nyomon követése
    private JatekScores jatekScores; // Hivatkozás a JatekScores osztályra
    private Image snakeHeadImageD;
    private Image snakeHeadImageU;
    private Image snakeHeadImageR;
    private Image snakeHeadImageL;
    private Image snakeBodyImageDU;
    private Image snakeBodyImageRL;
    private Image snakeBodyImageDR;
    private Image snakeBodyImageDL;
    private Image snakeBodyImageLU;
    private Image snakeBodyImageRU;
    private Image snakeTailImageD;
    private Image snakeTailImageDL;
    private Image snakeTailImageDR;
    private Image snakeTailImageU;
    private Image snakeTailImageUL;
    private Image snakeTailImageUR;
    private Image snakeTailImageR;
    private Image snakeTailImageRD;
    private Image snakeTailImageRU;
    private Image snakeTailImageL;
    private Image snakeTailImageLD;
    private Image snakeTailImageLU;

    private Image fruitImage;
    private ArrayList<Character> SnakeDirections;
    public enum ControlScheme {
        WASD, ARROW_KEYS
    }

    private ControlScheme controlScheme;
    private boolean inputProcessed = false;
	private int BOARD_WIDHT;

    //private Image backgroundImage;

    public SnakeJatek(JFrame gameFrame, JatekScores jatekScores, String playerName) {
    	//if(controlScheme!=ControlScheme.WASD && controlScheme!=ControlScheme.ARROW_KEYS)
    		//chooseControlScheme();
    	
        this.gameFrame = gameFrame;
        this.jatekScores = jatekScores;
        this.playername = playerName;
        setPreferredSize(new Dimension(BOARD_WIDTH * TILE_SIZE, BOARD_HEIGHT * TILE_SIZE));
        setBackground(Color.GREEN);
        snakeHeadImageD = new ImageIcon("./resources/KigyoFejD.png").getImage();
        snakeHeadImageU = new ImageIcon("./resources/KigyoFejU.png").getImage();
        snakeHeadImageR = new ImageIcon("./resources/KigyoFejR.png").getImage();
        snakeHeadImageL = new ImageIcon("./resources/KigyoFejL.png").getImage();
        snakeBodyImageDU = new ImageIcon("./resources/KigyoTestDU.png").getImage();
        snakeBodyImageRL = new ImageIcon("./resources/KigyoTestLR.png").getImage();
        snakeBodyImageDR = new ImageIcon("./resources/KigyoFordDR.png").getImage();
        snakeBodyImageDL = new ImageIcon("./resources/KigyoFordDL.png").getImage();
        snakeBodyImageLU = new ImageIcon("./resources/KigyoFordLU.png").getImage();
        snakeBodyImageRU = new ImageIcon("./resources/KigyoFordRU.png").getImage();
        snakeTailImageD = new ImageIcon("./resources/KigyoVegD.png").getImage();
        snakeTailImageDL = new ImageIcon("./resources/KigyoVegDL.png").getImage();
        snakeTailImageDR = new ImageIcon("./resources/KigyoVegDR.png").getImage();
        snakeTailImageU = new ImageIcon("./resources/KigyoVegU.png").getImage();
        snakeTailImageUL = new ImageIcon("./resources/KigyoVegUL.png").getImage();
        snakeTailImageUR = new ImageIcon("./resources/KigyoVegUR.png").getImage();
        snakeTailImageR = new ImageIcon("./resources/KigyoVegR.png").getImage();
        snakeTailImageRU = new ImageIcon("./resources/KigyoVegRU.png").getImage();
        snakeTailImageRD = new ImageIcon("./resources/KigyoVegRD.png").getImage();
        snakeTailImageL = new ImageIcon("./resources/KigyoVegL.png").getImage();
        snakeTailImageLU = new ImageIcon("./resources/KigyoVegLU.png").getImage();
        snakeTailImageLD = new ImageIcon("./resources/KigyoVegLD.png").getImage();
        fruitImage = new ImageIcon("./resources/Gyumolcs.png").getImage();
        //backgroundImage = new ImageIcon("./resources/background.png").getImage();

        snake = new ArrayList<>();
        snake.add(new Point(BOARD_WIDTH / 2, BOARD_HEIGHT / 2));
        SnakeDirections = new ArrayList<>();

        placeFood();

        timer = new Timer(150, this);
        timer.start();

        setFocusable(true);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent e) {
                int key = e.getKeyCode();
                if (inputProcessed) {
                    return;
                }

                // Handle controls based on the chosen scheme
                if (controlScheme == ControlScheme.WASD) {
                    // WASD Controls
                    switch (key) {
                        case java.awt.event.KeyEvent.VK_A: // 'A' key for LEFT
                            if (direction != 'R') direction = 'L';
                            break;
                        case java.awt.event.KeyEvent.VK_D: // 'D' key for RIGHT
                            if (direction != 'L') direction = 'R';
                            break;
                        case java.awt.event.KeyEvent.VK_W: // 'W' key for UP
                            if (direction != 'D') direction = 'U';
                            break;
                        case java.awt.event.KeyEvent.VK_S: // 'S' key for DOWN
                            if (direction != 'U') direction = 'D';
                            break;
                    }
                } else {
                    // Arrow Key Controls
                    switch (key) {
                        case java.awt.event.KeyEvent.VK_LEFT: // Left arrow
                            if (direction != 'R') direction = 'L';
                            break;
                        case java.awt.event.KeyEvent.VK_RIGHT: // Right arrow
                            if (direction != 'L') direction = 'R';
                            break;
                        case java.awt.event.KeyEvent.VK_UP: // Up arrow
                            if (direction != 'D') direction = 'U';
                            break;
                        case java.awt.event.KeyEvent.VK_DOWN: // Down arrow
                            if (direction != 'U') direction = 'D';
                            break;
                    }
                }
                inputProcessed = true;
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int x = 0; x < BOARD_WIDTH; x++) {
            for (int y = 0; y < BOARD_HEIGHT; y++) {
                g.setColor(Color.GRAY);
            }
        }


        if(SnakeDirections.size()>0) {
	        switch (SnakeDirections.get(0)){
	        case 'D':g.drawImage(snakeHeadImageD, snake.get(0).x* TILE_SIZE, snake.get(0).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
	        case 'U':g.drawImage(snakeHeadImageU, snake.get(0).x* TILE_SIZE, snake.get(0).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
	        case 'R':g.drawImage(snakeHeadImageR, snake.get(0).x* TILE_SIZE, snake.get(0).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
	        case 'L':g.drawImage(snakeHeadImageL, snake.get(0).x* TILE_SIZE, snake.get(0).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
	        }
        }
        if(SnakeDirections.size()>1) {
	        for (int j  =  1 ; j < snake.size()-1; j++) {
	        	if( SnakeDirections.get(j) == SnakeDirections.get(j-1)) {
		        	switch (SnakeDirections.get(j)){
		            case 'D':g.drawImage(snakeBodyImageDU, snake.get(j).x* TILE_SIZE, snake.get(j).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
		            case 'U':g.drawImage(snakeBodyImageDU, snake.get(j).x* TILE_SIZE, snake.get(j).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
		            case 'R':g.drawImage(snakeBodyImageRL, snake.get(j).x* TILE_SIZE, snake.get(j).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
		            case 'L':g.drawImage(snakeBodyImageRL, snake.get(j).x* TILE_SIZE, snake.get(j).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
		            }
	        	}else {
		        	switch (SnakeDirections.get(j)){
		            case 'D':
		            	switch (SnakeDirections.get(j-1)){
			            case 'U':g.drawImage(snakeBodyImageDU, snake.get(j).x* TILE_SIZE, snake.get(j).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            case 'R':g.drawImage(snakeBodyImageRU, snake.get(j).x* TILE_SIZE, snake.get(j).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            case 'L':g.drawImage(snakeBodyImageLU, snake.get(j).x* TILE_SIZE, snake.get(j).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            }
		            	break;
		            case 'U':
		            	switch (SnakeDirections.get(j-1)){
			            case 'D':g.drawImage(snakeBodyImageDU, snake.get(j).x* TILE_SIZE, snake.get(j).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            case 'R':g.drawImage(snakeBodyImageDR, snake.get(j).x* TILE_SIZE, snake.get(j).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            case 'L':g.drawImage(snakeBodyImageDL, snake.get(j).x* TILE_SIZE, snake.get(j).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            }
		            	break;
		            case 'R':
		            	switch (SnakeDirections.get(j-1)){
			            case 'D':g.drawImage(snakeBodyImageDL, snake.get(j).x* TILE_SIZE, snake.get(j).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            case 'U':g.drawImage(snakeBodyImageLU, snake.get(j).x* TILE_SIZE, snake.get(j).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            case 'L':g.drawImage(snakeBodyImageRL, snake.get(j).x* TILE_SIZE, snake.get(j).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            }
		            	break;
		            case 'L':
		            	switch (SnakeDirections.get(j-1)){
			            case 'D':g.drawImage(snakeBodyImageDR, snake.get(j).x* TILE_SIZE, snake.get(j).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            case 'U':g.drawImage(snakeBodyImageRU, snake.get(j).x* TILE_SIZE, snake.get(j).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            case 'R':g.drawImage(snakeBodyImageRL, snake.get(j).x* TILE_SIZE, snake.get(j).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            }
		            	break;
		            }
	        		
	        	}
	        	
	        	
		    }
        
        }
        if(SnakeDirections.size()>1) {
        	if(snake.size()>1)
        		if(SnakeDirections.get(snake.size()-2) == SnakeDirections.get(snake.size()-1)) {
			        switch (SnakeDirections.get(snake.size()-1)){
			        case 'D':g.drawImage(snakeTailImageD, snake.get(snake.size()-1).x* TILE_SIZE, snake.get(snake.size()-1).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			        case 'U':g.drawImage(snakeTailImageU, snake.get(snake.size()-1).x* TILE_SIZE, snake.get(snake.size()-1).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			        case 'R':g.drawImage(snakeTailImageR, snake.get(snake.size()-1).x* TILE_SIZE, snake.get(snake.size()-1).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			        case 'L':g.drawImage(snakeTailImageL, snake.get(snake.size()-1).x* TILE_SIZE, snake.get(snake.size()-1).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			        }
        		}else {
        			switch (SnakeDirections.get(snake.size()-1)){
		            case 'D':
		            	switch (SnakeDirections.get(snake.size()-2)){
			            case 'R':g.drawImage(snakeTailImageRU, snake.get(snake.size()-1).x* TILE_SIZE, snake.get(snake.size()-1).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            case 'L':g.drawImage(snakeTailImageLU, snake.get(snake.size()-1).x* TILE_SIZE, snake.get(snake.size()-1).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            }
		            	break;
		            case 'U':
		            	switch (SnakeDirections.get(snake.size()-2)){
			            case 'R':g.drawImage(snakeTailImageRD, snake.get(snake.size()-1).x* TILE_SIZE, snake.get(snake.size()-1).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            case 'L':g.drawImage(snakeTailImageLD, snake.get(snake.size()-1).x* TILE_SIZE, snake.get(snake.size()-1).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            }
		            	break;
		            case 'R':
		            	switch (SnakeDirections.get(snake.size()-2)){
			            case 'D':g.drawImage(snakeTailImageDL, snake.get(snake.size()-1).x* TILE_SIZE, snake.get(snake.size()-1).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            case 'U':g.drawImage(snakeTailImageUL, snake.get(snake.size()-1).x* TILE_SIZE, snake.get(snake.size()-1).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            }
		            	break;
		            case 'L':
		            	switch (SnakeDirections.get(snake.size()-2)){
			            case 'D':g.drawImage(snakeTailImageDR, snake.get(snake.size()-1).x* TILE_SIZE, snake.get(snake.size()-1).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            case 'U':g.drawImage(snakeTailImageUR, snake.get(snake.size()-1).x* TILE_SIZE, snake.get(snake.size()-1).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            }
		            	break;
		            }
        		}
        	
        }
        
        


        g.drawImage(fruitImage, food.x* TILE_SIZE, food.y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
        	inputProcessed = false;
            moveSnake();
            checkCollision();
            checkFood();
        } else {
            endGame();
        }
        repaint();
    }
    
    private void chooseControlScheme() {
        Object[] options = {"WASD", "Arrow Keys"};
        int choice = JOptionPane.showOptionDialog(this,
                "Choose your control scheme:",
                "Control Scheme Selection",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        if (choice == 0) {
            controlScheme = ControlScheme.WASD;
        } else {
            controlScheme = ControlScheme.ARROW_KEYS;
        }
    }

    public void moveSnake() {
        Point head = snake.get(0);
        Point newHead = new Point(head);

        switch (direction) {
            case 'L': newHead.x--; break;
            case 'R': newHead.x++; break;
            case 'U': newHead.y--; break;
            case 'D': newHead.y++; break;
        }

        snake.add(0, newHead);
        snake.remove(snake.size() - 1);
        SnakeDirections.add(0, direction); // Update head direction
        
    }

    public void checkCollision() {
        Point head = snake.get(0);

        if (head.x < 0 || head.x >= BOARD_WIDTH || head.y < 0 || head.y >= BOARD_HEIGHT) {
            running = false;
        }

        for (int i = 1; i < snake.size(); i++) {
            if (head.equals(snake.get(i))) {
                running = false;
                break;
            }
        }
    }

    public void checkFood() {
        Point head = snake.get(0);
        if (head.equals(food)) {
            score += 10; // Pontszám növelése gyümölcs elfogyasztásakor
            snake.add(new Point(-1, -1));
            placeFood();
        }
    }

    public void placeFood() {
        Random rand = new Random();
        boolean validPosition = false;

        while (!validPosition) {
            int x = rand.nextInt(BOARD_WIDTH);
            int y = rand.nextInt(BOARD_HEIGHT);
            Point potentialFoodPosition = new Point(x, y);

            validPosition = true;
            for (Point part : snake) {
                if (part.equals(potentialFoodPosition)) {
                    validPosition = false;
                    break;
                }
            }

            if (validPosition) {
                food = potentialFoodPosition;
            }
        }
    }

    private void endGame() {
        timer.stop();
        JOptionPane.showMessageDialog(this, "Játék vége! Végső pontszám: " + score, "Vége", JOptionPane.INFORMATION_MESSAGE);
        
        // Pontszám hozzáadása a scoreboard-hoz
        jatekScores.addPlayerScore(playername,score);
        gameFrame.dispose();
    }
    public void setSnake(ArrayList<Point> s) {
    	this.snake = s;
    }
    public ArrayList<Point> getSnake() {
    	return this.snake;
    }
    public void setDirection(char d) {
    	this.direction = d;
    }

    public void setFood(Point f) {
    	this.food = f;
    }
    public Point getFood() {
    	return this.food;
    }
    public int getScore() {
    	return this.score;
    }

	public int getBoardWidth() {
		return this.BOARD_WIDTH;
	}

	public int getBoardHeight() {
		return this.BOARD_HEIGHT;
	}

	public void setBoardDimensions(int i, int j) {
		this.BOARD_HEIGHT=i;
		this.BOARD_WIDHT=j;
		
	}

	public Boolean isRunning() {
		return running;
	}
}