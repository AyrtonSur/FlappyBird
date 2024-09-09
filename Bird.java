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
    private int birdWidth = 34;
    private int birdHeight = 24;
    private Image img;
    private int velocityY = -6;

    Bird(Image img, int width, int height) {
        this.img = img;
        this.birdX = width/8;
        this.birdY = height/2;
    }

    public void move() {
        this.birdY += velocityY;
    }

    public int getBirdHeight() {
        return birdHeight;
    }

    public int getBirdWidth() {
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
}
