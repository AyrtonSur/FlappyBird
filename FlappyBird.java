import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    private static int boardWidth = 360;
    private static int boardHeight = 640;

    //Images
    private Image backgroundImage;
    private Image birdImage;
    private Image topPipeImage;
    private Image bottomPipeImage;

    //Bird
    private Bird bird;

    //Pipes
    private ArrayList<Pipe> pipes;

    //Timer for gameloop
    private Timer gameLoop;

    //Timer por pipes
    private Timer placePipesTimer;

    //Variable to decide if game is over
    private static boolean gameOver = false;

    //Score of the game
    private double score = 0;


    FlappyBird() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));

        setFocusable(true);
        addKeyListener(this);

        SoundEffect.loadSound("jump", "SoundEffects/jump.wav", 0.1f);
        SoundEffect.loadSound("death", "SoundEffects/death.wav", 0.1f);

        //loading images
        backgroundImage = new ImageIcon(getClass().getResource("./Sprites/flappybirdbg.png")).getImage();
        birdImage = new ImageIcon(getClass().getResource("./Sprites/flappybird.png")).getImage();
        topPipeImage = new ImageIcon(getClass().getResource("./Sprites/toppipe.png")).getImage();
        bottomPipeImage = new ImageIcon(getClass().getResource("./Sprites/bottompipe.png")).getImage();


        //Creating bird
        bird = new Bird(birdImage, boardWidth, boardHeight);
        pipes = new ArrayList<Pipe>();

        //place pipes timer
        placePipesTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                placePipes();
            }
        });

        placePipesTimer.start();

        //Game timer
        gameLoop = new Timer(1000/60, this);
        gameLoop.start();
    }

    public void placePipes(){
        int randomPipeY = (int) (0 - Pipe.getPipeHeight()/4 - Math.random()*(Pipe.getPipeHeight()/2));
        int openingSpace = boardHeight/4;

        Pipe topPipe = new Pipe(boardWidth, 0, topPipeImage);
        topPipe.setPipeY(randomPipeY);
        pipes.add(topPipe);

        Pipe bottomPipe = new Pipe(boardWidth, 0, bottomPipeImage);
        bottomPipe.setPipeY(topPipe.getPipeY() + Pipe.getPipeHeight() + openingSpace);
        pipes.add(bottomPipe);

    }

    public void draw(Graphics g) {
        //Background
        g.drawImage(backgroundImage, 0, 0, boardWidth, boardHeight, null);

        //Bird
        g.drawImage(bird.getImg(), bird.getBirdX(), bird.getBirdY(), Bird.getBirdWidth(), Bird.getBirdHeight(), null);

        //Pipes
        for (Pipe p: pipes){
            g.drawImage(p.getImg(), p.getPipeX(), p.getPipeY(), Pipe.getPipeWidth(), Pipe.getPipeHeight(), null);
        }

        //Score
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 32));
        if (gameOver) {
            g.drawString("Game Over", boardWidth/2-85, boardHeight/2);
            g.drawString(String.valueOf((int) score), 10, 35);
        }
        else {
            g.drawString(String.valueOf((int) score), 10, 35);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public boolean collision(Bird a, Pipe b) {
        return a.getBirdX() < b.getPipeX() + Pipe.getPipeWidth() && //a's top left corner does not reach b's top right corner
               a.getBirdX() + Bird.getBirdWidth() > b.getPipeX() && //a's top right corner passes b's top left corner
               a.getBirdY() < b.getPipeY() + Pipe.getPipeHeight() && //a's bottom right corner does not reach b's bottom left corner
               a.getBirdY() + Bird.getBirdHeight() > b.getPipeY(); //a's bottom left corner passes b's bottom right corner
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        bird.move();

        for (Pipe p: pipes){
            p.move();

            if(!p.getPassed() && bird.getBirdX() > p.getPipeX() + Pipe.getPipeWidth()){
                p.setPassed(true);
                score += 0.5;
            }

            if (collision(bird, p)) {
                setGameOver(true);
            }


        }

        if(gameOver) {
            placePipesTimer.stop();
            gameLoop.stop();
            SoundEffect.playSound("death");
        }

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            SoundEffect.playSound("jump");
            //set bird velocity to 9 everytime space is pressed
            bird.setVelocityY(9);
            if (gameOver) {
                //restast every variables to default
                bird.setVelocityY(0);
                bird.setBirdY(boardHeight/8);
                pipes.clear();
                score = 0;
                setGameOver(false);
                gameLoop.start();
                placePipesTimer.start();
            }

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    public static int getBoardHeight() {
        return boardHeight;
    }

    public static int getBoardWidth() {
        return boardWidth;
    }

    public static void setGameOver(boolean gameOver) {
        FlappyBird.gameOver = gameOver;
    }

}
