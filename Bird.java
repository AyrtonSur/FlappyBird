import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Bird {
    //Bird
    private int birdX;
    private int birdY;
    private static int birdWidth = 34;
    private static int birdHeight = 24;
    private Image img;
    private int velocityY = 0;
    private int gravity = 1;


    Bird(Image img, int width, int height) {
        this.img = img;
        this.birdX = width/8;
        this.birdY = height/2;
    }

    public void move() {
        velocityY -= gravity;
        this.birdY -= velocityY;

        //Para limitar até onde o passáro pode ir
        this.birdY = Math.max(this.birdY, 0);

        if (birdY > FlappyBird.getBoardHeight()){
            FlappyBird.setGameOver(true);
        }
    }

    public static int getBirdHeight() {
        return birdHeight;
    }

    public static int getBirdWidth() {
        return birdWidth;
    }

    public Image getImg() {
        return img;
    }

    public int getBirdX() {
        return birdX;
    }

    public int getBirdY() {
        return birdY;
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }
}
