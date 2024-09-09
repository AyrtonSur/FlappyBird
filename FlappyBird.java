import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;

public class FlappyBird extends JPanel {
    private int boardWidth = 360;
    private int boardHeight = 640;

    //Images
    private Image backgroundImage;
    private Image birdImage;
    private Image topPipeImage;
    private Image bottomPipeImage;

    //Bird
    private Bird bird;



    FlappyBird() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));

        backgroundImage = new ImageIcon(getClass().getResource("./Sprites/flappybirdbg.png")).getImage();
        birdImage = new ImageIcon(getClass().getResource("./Sprites/flappybird.png")).getImage();
        topPipeImage = new ImageIcon(getClass().getResource("./Sprites/toppipe.png")).getImage();
        bottomPipeImage = new ImageIcon(getClass().getResource("./Sprites/bottompipe.png")).getImage();

        bird = new Bird(birdImage, boardWidth, boardHeight);
    }

    public void draw(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, boardWidth, boardHeight, null);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }


}
