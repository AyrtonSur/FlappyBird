import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    private int boardWidth = 360;
    private int boardHeight = 640;

    //Images
    private Image backgroundImage;
    private Image birdImage;
    private Image topPipeImage;
    private Image bottomPipeImage;

    //Bird
    private Bird bird;

    Timer gameLoop;


    FlappyBird() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));

        setFocusable(true);
        addKeyListener(this);

        //loading images
        backgroundImage = new ImageIcon(getClass().getResource("./Sprites/flappybirdbg.png")).getImage();
        birdImage = new ImageIcon(getClass().getResource("./Sprites/flappybird.png")).getImage();
        topPipeImage = new ImageIcon(getClass().getResource("./Sprites/toppipe.png")).getImage();
        bottomPipeImage = new ImageIcon(getClass().getResource("./Sprites/bottompipe.png")).getImage();

        //Creating bird
        bird = new Bird(birdImage, boardWidth, boardHeight);

        //Game timer
        gameLoop = new Timer(1000/60, this);
        gameLoop.start();
    }

    public void draw(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, boardWidth, boardHeight, null);

        g.drawImage(bird.getImg(), bird.getBirdX(), bird.getBirdY(), bird.getBirdWidth(), bird.getBirdHeight(), null);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        bird.move();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            //set bird velocity to 9 everytime space is pressed
            bird.setVelocityY(9);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}


}
