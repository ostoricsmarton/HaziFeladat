package KigyoJatek;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

public class SnakeJatekMulti extends JPanel implements ActionListener {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int TILE_SIZE = 40;
    private int BOARD_WIDTH = 40;
    private int BOARD_HEIGHT = 24;
    
    private Timer timer;
    private ArrayList<Point> snake1;
    private ArrayList<Point> snake2;
    private Point food1;
    private Point food2;
    private char direction1 = 'R';
    private char direction2 = 'L';
    private boolean running = true;
    private JFrame gameFrame;
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
    private Image snakeHead2ImageD;
    private Image snakeHead2ImageU;
    private Image snakeHead2ImageR;
    private Image snakeHead2ImageL;
    private Image snakeBody2ImageDU;
    private Image snakeBody2ImageRL;
    private Image snakeBody2ImageDR;
    private Image snakeBody2ImageDL;
    private Image snakeBody2ImageLU;
    private Image snakeBody2ImageRU;
    private Image snakeTail2ImageD;
    private Image snakeTail2ImageDL;
    private Image snakeTail2ImageDR;
    private Image snakeTail2ImageU;
    private Image snakeTail2ImageUL;
    private Image snakeTail2ImageUR;
    private Image snakeTail2ImageR;
    private Image snakeTail2ImageRD;
    private Image snakeTail2ImageRU;
    private Image snakeTail2ImageL;
    private Image snakeTail2ImageLD;
    private Image snakeTail2ImageLU;
    private Image fruit2Image;
    
    private ArrayList<Character> SnakeDirections1;
    private ArrayList<Character> SnakeDirections2;
    private boolean inputProcessed1 = false;
    private boolean inputProcessed2 = false;


    private LinkedBlockingQueue<Character> player1InputQueue = new LinkedBlockingQueue<>();
    private LinkedBlockingQueue<Character> player2InputQueue = new LinkedBlockingQueue<>();

    

    //private Image backgroundImage;

    public SnakeJatekMulti(JFrame gameFrame) {
    	this.gameFrame = gameFrame;
    	
        setPreferredSize(new Dimension(BOARD_WIDTH * TILE_SIZE, BOARD_HEIGHT * TILE_SIZE));
        setBackground(Color.YELLOW);
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
        snakeHead2ImageD = new ImageIcon("./resources/KigyoFej2D.png").getImage();
        snakeHead2ImageU = new ImageIcon("./resources/KigyoFej2U.png").getImage();
        snakeHead2ImageR = new ImageIcon("./resources/KigyoFej2R.png").getImage();
        snakeHead2ImageL = new ImageIcon("./resources/KigyoFej2L.png").getImage();
        snakeBody2ImageDU = new ImageIcon("./resources/KigyoTest2DU.png").getImage();
        snakeBody2ImageRL = new ImageIcon("./resources/KigyoTest2LR.png").getImage();
        snakeBody2ImageDR = new ImageIcon("./resources/KigyoFord2DR.png").getImage();
        snakeBody2ImageDL = new ImageIcon("./resources/KigyoFord2DL.png").getImage();
        snakeBody2ImageLU = new ImageIcon("./resources/KigyoFord2LU.png").getImage();
        snakeBody2ImageRU = new ImageIcon("./resources/KigyoFord2RU.png").getImage();
        snakeTail2ImageD = new ImageIcon("./resources/KigyoVeg2D.png").getImage();
        snakeTail2ImageDL = new ImageIcon("./resources/KigyoVeg2DL.png").getImage();
        snakeTail2ImageDR = new ImageIcon("./resources/KigyoVeg2DR.png").getImage();
        snakeTail2ImageU = new ImageIcon("./resources/KigyoVeg2U.png").getImage();
        snakeTail2ImageUL = new ImageIcon("./resources/KigyoVeg2UL.png").getImage();
        snakeTail2ImageUR = new ImageIcon("./resources/KigyoVeg2UR.png").getImage();
        snakeTail2ImageR = new ImageIcon("./resources/KigyoVeg2R.png").getImage();
        snakeTail2ImageRU = new ImageIcon("./resources/KigyoVeg2RU.png").getImage();
        snakeTail2ImageRD = new ImageIcon("./resources/KigyoVeg2RD.png").getImage();
        snakeTail2ImageL = new ImageIcon("./resources/KigyoVeg2L.png").getImage();
        snakeTail2ImageLU = new ImageIcon("./resources/KigyoVeg2LU.png").getImage();
        snakeTail2ImageLD = new ImageIcon("./resources/KigyoVeg2LD.png").getImage();
        fruit2Image = new ImageIcon("./resources/Gyumolcs2.png").getImage();
        //backgroundImage = new ImageIcon("./resources/background.png").getImage();

        snake1 = new ArrayList<>();
        snake1.add(new Point((BOARD_WIDTH / 2)+1, BOARD_HEIGHT / 2));
        SnakeDirections1 = new ArrayList<>();
        snake2 = new ArrayList<>();
        snake2.add(new Point((BOARD_WIDTH / 2)-1, BOARD_HEIGHT / 2));
        SnakeDirections2 = new ArrayList<>();

        placeFood1();
        placeFood2();

        timer = new Timer(150, this);
        timer.start();

        setFocusable(true);
        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                // Handle player 1 inputs
                if (!inputProcessed1) {
                    switch (e.getKeyCode()) {
                        case java.awt.event.KeyEvent.VK_LEFT -> {
                            if (direction1 != 'R') {
                                player1InputQueue.offer('L');
                                inputProcessed1 = true; // Lock input for this tick
                            }
                        }
                        case java.awt.event.KeyEvent.VK_RIGHT -> {
                            if (direction1 != 'L') {
                                player1InputQueue.offer('R');
                                inputProcessed1 = true; // Lock input for this tick
                            }
                        }
                        case java.awt.event.KeyEvent.VK_UP -> {
                            if (direction1 != 'D') {
                                player1InputQueue.offer('U');
                                inputProcessed1 = true; // Lock input for this tick
                            }
                        }
                        case java.awt.event.KeyEvent.VK_DOWN -> {
                            if (direction1 != 'U') {
                                player1InputQueue.offer('D');
                                inputProcessed1 = true; // Lock input for this tick
                            }
                        }
                    }
                }

                // Handle player 2 inputs
                if (!inputProcessed2) {
                    switch (e.getKeyCode()) {
                        case java.awt.event.KeyEvent.VK_A -> {
                            if (direction2 != 'R') {
                                player2InputQueue.offer('L');
                                inputProcessed2 = true; // Lock input for this tick
                            }
                        }
                        case java.awt.event.KeyEvent.VK_D -> {
                            if (direction2 != 'L') {
                                player2InputQueue.offer('R');
                                inputProcessed2 = true; // Lock input for this tick
                            }
                        }
                        case java.awt.event.KeyEvent.VK_W -> {
                            if (direction2 != 'D') {
                                player2InputQueue.offer('U');
                                inputProcessed2 = true; // Lock input for this tick
                            }
                        }
                        case java.awt.event.KeyEvent.VK_S -> {
                            if (direction2 != 'U') {
                                player2InputQueue.offer('D');
                                inputProcessed2 = true; // Lock input for this tick
                            }
                        }
                    }
                }
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


        if(SnakeDirections1.size()>0) {
	        switch (SnakeDirections1.get(0)){
	        case 'D':g.drawImage(snakeHeadImageD, snake1.get(0).x* TILE_SIZE, snake1.get(0).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
	        case 'U':g.drawImage(snakeHeadImageU, snake1.get(0).x* TILE_SIZE, snake1.get(0).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
	        case 'R':g.drawImage(snakeHeadImageR, snake1.get(0).x* TILE_SIZE, snake1.get(0).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
	        case 'L':g.drawImage(snakeHeadImageL, snake1.get(0).x* TILE_SIZE, snake1.get(0).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
	        }
        }
        if(SnakeDirections1.size()>1) {
	        for (int j  =  1 ; j < snake1.size()-1; j++) {
	        	if( SnakeDirections1.get(j) == SnakeDirections1.get(j-1)) {
		        	switch (SnakeDirections1.get(j)){
		            case 'D':g.drawImage(snakeBodyImageDU, snake1.get(j).x* TILE_SIZE, snake1.get(j).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
		            case 'U':g.drawImage(snakeBodyImageDU, snake1.get(j).x* TILE_SIZE, snake1.get(j).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
		            case 'R':g.drawImage(snakeBodyImageRL, snake1.get(j).x* TILE_SIZE, snake1.get(j).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
		            case 'L':g.drawImage(snakeBodyImageRL, snake1.get(j).x* TILE_SIZE, snake1.get(j).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
		            }
	        	}else {
		        	switch (SnakeDirections1.get(j)){
		            case 'D':
		            	switch (SnakeDirections1.get(j-1)){
			            case 'U':g.drawImage(snakeBodyImageDU, snake1.get(j).x* TILE_SIZE, snake1.get(j).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            case 'R':g.drawImage(snakeBodyImageRU, snake1.get(j).x* TILE_SIZE, snake1.get(j).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            case 'L':g.drawImage(snakeBodyImageLU, snake1.get(j).x* TILE_SIZE, snake1.get(j).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            }
		            	break;
		            case 'U':
		            	switch (SnakeDirections1.get(j-1)){
			            case 'D':g.drawImage(snakeBodyImageDU, snake1.get(j).x* TILE_SIZE, snake1.get(j).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            case 'R':g.drawImage(snakeBodyImageDR, snake1.get(j).x* TILE_SIZE, snake1.get(j).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            case 'L':g.drawImage(snakeBodyImageDL, snake1.get(j).x* TILE_SIZE, snake1.get(j).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            }
		            	break;
		            case 'R':
		            	switch (SnakeDirections1.get(j-1)){
			            case 'D':g.drawImage(snakeBodyImageDL, snake1.get(j).x* TILE_SIZE, snake1.get(j).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            case 'U':g.drawImage(snakeBodyImageLU, snake1.get(j).x* TILE_SIZE, snake1.get(j).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            case 'L':g.drawImage(snakeBodyImageRL, snake1.get(j).x* TILE_SIZE, snake1.get(j).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            }
		            	break;
		            case 'L':
		            	switch (SnakeDirections1.get(j-1)){
			            case 'D':g.drawImage(snakeBodyImageDR, snake1.get(j).x* TILE_SIZE, snake1.get(j).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            case 'U':g.drawImage(snakeBodyImageRU, snake1.get(j).x* TILE_SIZE, snake1.get(j).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            case 'R':g.drawImage(snakeBodyImageRL, snake1.get(j).x* TILE_SIZE, snake1.get(j).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            }
		            	break;
		            }
	        		
	        	}
	        	
	        	
		    }
        
        }
        if(SnakeDirections1.size()>1) {
        	if(snake1.size()>1)
        		if(SnakeDirections1.get(snake1.size()-2) == SnakeDirections1.get(snake1.size()-1)) {
			        switch (SnakeDirections1.get(snake1.size()-1)){
			        case 'D':g.drawImage(snakeTailImageD, snake1.get(snake1.size()-1).x* TILE_SIZE, snake1.get(snake1.size()-1).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			        case 'U':g.drawImage(snakeTailImageU, snake1.get(snake1.size()-1).x* TILE_SIZE, snake1.get(snake1.size()-1).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			        case 'R':g.drawImage(snakeTailImageR, snake1.get(snake1.size()-1).x* TILE_SIZE, snake1.get(snake1.size()-1).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			        case 'L':g.drawImage(snakeTailImageL, snake1.get(snake1.size()-1).x* TILE_SIZE, snake1.get(snake1.size()-1).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			        }
        		}else {
        			switch (SnakeDirections1.get(snake1.size()-1)){
		            case 'D':
		            	switch (SnakeDirections1.get(snake1.size()-2)){
			            case 'R':g.drawImage(snakeTailImageRU, snake1.get(snake1.size()-1).x* TILE_SIZE, snake1.get(snake1.size()-1).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            case 'L':g.drawImage(snakeTailImageLU, snake1.get(snake1.size()-1).x* TILE_SIZE, snake1.get(snake1.size()-1).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            }
		            	break;
		            case 'U':
		            	switch (SnakeDirections1.get(snake1.size()-2)){
			            case 'R':g.drawImage(snakeTailImageRD, snake1.get(snake1.size()-1).x* TILE_SIZE, snake1.get(snake1.size()-1).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            case 'L':g.drawImage(snakeTailImageLD, snake1.get(snake1.size()-1).x* TILE_SIZE, snake1.get(snake1.size()-1).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            }
		            	break;
		            case 'R':
		            	switch (SnakeDirections1.get(snake1.size()-2)){
			            case 'D':g.drawImage(snakeTailImageDL, snake1.get(snake1.size()-1).x* TILE_SIZE, snake1.get(snake1.size()-1).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            case 'U':g.drawImage(snakeTailImageUL, snake1.get(snake1.size()-1).x* TILE_SIZE, snake1.get(snake1.size()-1).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            }
		            	break;
		            case 'L':
		            	switch (SnakeDirections1.get(snake1.size()-2)){
			            case 'D':g.drawImage(snakeTailImageDR, snake1.get(snake1.size()-1).x* TILE_SIZE, snake1.get(snake1.size()-1).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            case 'U':g.drawImage(snakeTailImageUR, snake1.get(snake1.size()-1).x* TILE_SIZE, snake1.get(snake1.size()-1).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            }
		            	break;
		            }
        		}
        	
        }
        
        if(SnakeDirections2.size()>0) {
	        switch (SnakeDirections2.get(0)){
	        case 'D':g.drawImage(snakeHead2ImageD, snake2.get(0).x* TILE_SIZE, snake2.get(0).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
	        case 'U':g.drawImage(snakeHead2ImageU, snake2.get(0).x* TILE_SIZE, snake2.get(0).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
	        case 'R':g.drawImage(snakeHead2ImageR, snake2.get(0).x* TILE_SIZE, snake2.get(0).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
	        case 'L':g.drawImage(snakeHead2ImageL, snake2.get(0).x* TILE_SIZE, snake2.get(0).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
	        }
        }
        if(SnakeDirections2.size()>1) {
	        for (int j  =  1 ; j < snake2.size()-1; j++) {
	        	if( SnakeDirections2.get(j) == SnakeDirections2.get(j-1)) {
		        	switch (SnakeDirections2.get(j)){
		            case 'D':g.drawImage(snakeBody2ImageDU, snake2.get(j).x* TILE_SIZE, snake2.get(j).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
		            case 'U':g.drawImage(snakeBody2ImageDU, snake2.get(j).x* TILE_SIZE, snake2.get(j).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
		            case 'R':g.drawImage(snakeBody2ImageRL, snake2.get(j).x* TILE_SIZE, snake2.get(j).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
		            case 'L':g.drawImage(snakeBody2ImageRL, snake2.get(j).x* TILE_SIZE, snake2.get(j).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
		            }
	        	}else {
		        	switch (SnakeDirections2.get(j)){
		            case 'D':
		            	switch (SnakeDirections2.get(j-1)){
			            case 'U':g.drawImage(snakeBody2ImageDU, snake2.get(j).x* TILE_SIZE, snake2.get(j).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            case 'R':g.drawImage(snakeBody2ImageRU, snake2.get(j).x* TILE_SIZE, snake2.get(j).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            case 'L':g.drawImage(snakeBody2ImageLU, snake2.get(j).x* TILE_SIZE, snake2.get(j).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            }
		            	break;
		            case 'U':
		            	switch (SnakeDirections2.get(j-1)){
			            case 'D':g.drawImage(snakeBody2ImageDU, snake2.get(j).x* TILE_SIZE, snake2.get(j).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            case 'R':g.drawImage(snakeBody2ImageDR, snake2.get(j).x* TILE_SIZE, snake2.get(j).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            case 'L':g.drawImage(snakeBody2ImageDL, snake2.get(j).x* TILE_SIZE, snake2.get(j).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            }
		            	break;
		            case 'R':
		            	switch (SnakeDirections2.get(j-1)){
			            case 'D':g.drawImage(snakeBody2ImageDL, snake2.get(j).x* TILE_SIZE, snake2.get(j).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            case 'U':g.drawImage(snakeBody2ImageLU, snake2.get(j).x* TILE_SIZE, snake2.get(j).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            case 'L':g.drawImage(snakeBody2ImageRL, snake2.get(j).x* TILE_SIZE, snake2.get(j).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            }
		            	break;
		            case 'L':
		            	switch (SnakeDirections2.get(j-1)){
			            case 'D':g.drawImage(snakeBody2ImageDR, snake2.get(j).x* TILE_SIZE, snake2.get(j).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            case 'U':g.drawImage(snakeBody2ImageRU, snake2.get(j).x* TILE_SIZE, snake2.get(j).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            case 'R':g.drawImage(snakeBody2ImageRL, snake2.get(j).x* TILE_SIZE, snake2.get(j).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            }
		            	break;
		            }
	        		
	        	}
	        	
	        	
		    }
        
        }
        if(SnakeDirections2.size()>1) {
        	if(snake2.size()>1)
        		if(SnakeDirections2.get(snake2.size()-2) == SnakeDirections2.get(snake2.size()-1)) {
			        switch (SnakeDirections2.get(snake2.size()-1)){
			        case 'D':g.drawImage(snakeTail2ImageD, snake2.get(snake2.size()-1).x* TILE_SIZE, snake2.get(snake2.size()-1).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			        case 'U':g.drawImage(snakeTail2ImageU, snake2.get(snake2.size()-1).x* TILE_SIZE, snake2.get(snake2.size()-1).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			        case 'R':g.drawImage(snakeTail2ImageR, snake2.get(snake2.size()-1).x* TILE_SIZE, snake2.get(snake2.size()-1).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			        case 'L':g.drawImage(snakeTail2ImageL, snake2.get(snake2.size()-1).x* TILE_SIZE, snake2.get(snake2.size()-1).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			        }
        		}else {
        			switch (SnakeDirections2.get(snake2.size()-1)){
		            case 'D':
		            	switch (SnakeDirections2.get(snake2.size()-2)){
			            case 'R':g.drawImage(snakeTail2ImageRU, snake2.get(snake2.size()-1).x* TILE_SIZE, snake2.get(snake2.size()-1).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            case 'L':g.drawImage(snakeTail2ImageLU, snake2.get(snake2.size()-1).x* TILE_SIZE, snake2.get(snake2.size()-1).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            }
		            	break;
		            case 'U':
		            	switch (SnakeDirections2.get(snake2.size()-2)){
			            case 'R':g.drawImage(snakeTail2ImageRD, snake2.get(snake2.size()-1).x* TILE_SIZE, snake2.get(snake2.size()-1).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            case 'L':g.drawImage(snakeTail2ImageLD, snake2.get(snake2.size()-1).x* TILE_SIZE, snake2.get(snake2.size()-1).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            }
		            	break;
		            case 'R':
		            	switch (SnakeDirections2.get(snake2.size()-2)){
			            case 'D':g.drawImage(snakeTail2ImageDL, snake2.get(snake2.size()-1).x* TILE_SIZE, snake2.get(snake2.size()-1).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            case 'U':g.drawImage(snakeTail2ImageUL, snake2.get(snake2.size()-1).x* TILE_SIZE, snake2.get(snake2.size()-1).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            }
		            	break;
		            case 'L':
		            	switch (SnakeDirections2.get(snake2.size()-2)){
			            case 'D':g.drawImage(snakeTail2ImageDR, snake2.get(snake2.size()-1).x* TILE_SIZE, snake2.get(snake2.size()-1).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            case 'U':g.drawImage(snakeTail2ImageUR, snake2.get(snake2.size()-1).x* TILE_SIZE, snake2.get(snake2.size()-1).y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);break;
			            }
		            	break;
		            }
        		}
        	
        }
        
        


        g.drawImage(fruitImage, food1.x* TILE_SIZE, food1.y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);
        g.drawImage(fruit2Image, food2.x* TILE_SIZE, food2.y* TILE_SIZE, TILE_SIZE, TILE_SIZE, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            inputProcessed1 = false;
            inputProcessed2 = false;
        	Character input1 = player1InputQueue.poll();
            if (input1 != null) {
                direction1 = input1;
            }

            // Process player 2 inputs
            Character input2 = player2InputQueue.poll();
            if (input2 != null) {
                direction2 = input2;
            }
            moveSnake1();
            moveSnake2();
            checkCollision();
            checkFood();
        } else {
            endGame();
        }
        repaint();
    }
    

    public void moveSnake1() {
        Point head = snake1.get(0);
        Point newHead = new Point(head);

        switch (direction1) {
            case 'L': newHead.x--; break;
            case 'R': newHead.x++; break;
            case 'U': newHead.y--; break;
            case 'D': newHead.y++; break;
        }

        snake1.add(0, newHead);
        snake1.remove(snake1.size() - 1);
        SnakeDirections1.add(0, direction1); // Update head direction
        
    }
    
    public void moveSnake2() {
        Point head = snake2.get(0);
        Point newHead = new Point(head);

        switch (direction2) {
            case 'L': newHead.x--; break;
            case 'R': newHead.x++; break;
            case 'U': newHead.y--; break;
            case 'D': newHead.y++; break;
        }

        snake2.add(0, newHead);
        snake2.remove(snake2.size() - 1);
        SnakeDirections2.add(0, direction2); // Update head direction
        
    }


    public void checkCollision() {
        Point head1 = snake1.get(0);
        Point head2 = snake2.get(0);

        if (head1.x < 0 || head1.x >= BOARD_WIDTH || head1.y < 0 || head1.y >= BOARD_HEIGHT) {
            running = false;
        }

        for (int i = 1; i < snake1.size(); i++) {
            if (head1.equals(snake1.get(i))) {
                running = false;
                break;
            }

        }
        for (int i = 0; i < snake2.size(); i++) {
        	if (head1.equals(snake2.get(i))) {
                running = false;
                break;
            }

        }
        

        if (head2.x < 0 || head2.x >= BOARD_WIDTH || head2.y < 0 || head2.y >= BOARD_HEIGHT) {
            running = false;
        }

        for (int i = 1; i < snake2.size(); i++) {
            if (head2.equals(snake2.get(i))) {
                running = false;
                break;
            }

        }
        
        for (int i = 0; i < snake1.size(); i++) {
            if (head2.equals(snake1.get(i))) {
                running = false;
                break;
            }
            
        }
        
    }

    public void checkFood() {
        Point head1 = snake1.get(0);
        Point head2 = snake2.get(0);
        if (head1.equals(food1)) {
            snake1.add(new Point(-1, -1));
            placeFood1();
        }
        if (head1.equals(food2)) {
            snake1.add(new Point(-1, -1));
            placeFood2();
        }
        if(head2.equals(food1)) {
        	snake2.add(new Point(-1, -1));
            placeFood1();
        }
        if(head2.equals(food2)) {
        	snake2.add(new Point(-1, -1));
            placeFood2();
        }
    }

    public void placeFood1() {
        Random rand1 = new Random();
        boolean validPosition1 = false;
        

        while (!validPosition1) {
            int x = rand1.nextInt(BOARD_WIDTH);
            int y = rand1.nextInt(BOARD_HEIGHT);
            Point potentialFoodPosition = new Point(x, y);

            validPosition1 = true;
            for (Point part : snake1) {
                if (part.equals(potentialFoodPosition)) {
                    validPosition1 = false;
                    break;
                }
            }

            if (validPosition1) {
                food1 = potentialFoodPosition;
            }
        }
    }
    
    public void placeFood2() {
        Random rand2 = new Random();
        boolean validPosition2 = false;
        

        while (!validPosition2) {
            int x = rand2.nextInt(BOARD_WIDTH);
            int y = rand2.nextInt(BOARD_HEIGHT);
            Point potentialFoodPosition = new Point(x, y);

            validPosition2 = true;
            for (Point part : snake2) {
                if (part.equals(potentialFoodPosition)) {
                    validPosition2 = false;
                    break;
                }
            }

            if (validPosition2) {
                food2 = potentialFoodPosition;
            }
        }
    }

    private void endGame() {
        timer.stop();
        JOptionPane.showMessageDialog(this, "Játék vége! ", "Kilépés", JOptionPane.INFORMATION_MESSAGE);
        
        gameFrame.dispose();
    }
    
    public void setSnake1(ArrayList<Point> s) {
    	this.snake1 = s;
    }
    
    public ArrayList<Point> getSnake1() {
    	return this.snake1;
    }
    
    public void setDirection1(char d) {
    	this.direction1 = d;
    }
    
    public void setSnake2(ArrayList<Point> s) {
    	this.snake2 = s;
    }
    public ArrayList<Point> getSnake2() {
    	return this.snake2;
    }
    
    public void setDirection2(char d) {
    	this.direction2 = d;
    }

    public void setFood1(Point f) {
    	this.food1 = f;
    }
    
    public Point getFood1() {
    	return this.food1;
    }
    
    public void setFood2(Point f) {
    	this.food2 = f;
    }
    
    public Point getFood2() {
    	return this.food2;
    }


	public Boolean isRunning() {
		return running;
	}
	public void setRunning(Boolean b) {
		this.running = b;
	}
}