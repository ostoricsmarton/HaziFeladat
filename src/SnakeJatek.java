import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class SnakeJatek extends JPanel implements ActionListener {
    
    private final int TILE_SIZE = 25;
    private final int BOARD_WIDTH = 20;
    private final int BOARD_HEIGHT = 20;
    
    private Timer timer;
    private ArrayList<Point> snake;
    private Point food;
    private char direction = 'R';
    private boolean running = true;
    private JFrame gameFrame;
    private int score = 0; // Pontszám nyomon követése
    private JatekScores jatekScores; // Hivatkozás a JatekScores osztályra

    public SnakeJatek(JFrame gameFrame, JatekScores jatekScores) {
        this.gameFrame = gameFrame;
        this.jatekScores = jatekScores;
        setPreferredSize(new Dimension(BOARD_WIDTH * TILE_SIZE, BOARD_HEIGHT * TILE_SIZE));
        setBackground(Color.BLACK);

        snake = new ArrayList<>();
        snake.add(new Point(BOARD_WIDTH / 2, BOARD_HEIGHT / 2));
        placeFood();

        timer = new Timer(150, this);
        timer.start();

        setFocusable(true);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent e) {
                switch (e.getKeyCode()) {
                    case java.awt.event.KeyEvent.VK_LEFT:
                        if (direction != 'R') direction = 'L';
                        break;
                    case java.awt.event.KeyEvent.VK_RIGHT:
                        if (direction != 'L') direction = 'R';
                        break;
                    case java.awt.event.KeyEvent.VK_UP:
                        if (direction != 'D') direction = 'U';
                        break;
                    case java.awt.event.KeyEvent.VK_DOWN:
                        if (direction != 'U') direction = 'D';
                        break;
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
                g.drawRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }

        g.setColor(Color.GREEN);
        for (Point point : snake) {
            g.fillRect(point.x * TILE_SIZE, point.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }

        g.setColor(Color.RED);
        g.fillRect(food.x * TILE_SIZE, food.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            moveSnake();
            checkCollision();
            checkFood();
        } else {
            endGame();
        }
        repaint();
    }

    private void moveSnake() {
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
    }

    private void checkCollision() {
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

    private void checkFood() {
        Point head = snake.get(0);
        if (head.equals(food)) {
            score += 10; // Pontszám növelése gyümölcs elfogyasztásakor
            snake.add(new Point(-1, -1));
            placeFood();
        }
    }

    private void placeFood() {
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
        jatekScores.addScore(score);
        gameFrame.dispose();
    }
}