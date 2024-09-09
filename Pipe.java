import java.awt.*;
public class Pipe {
    private int pipeX;
    private int pipeY;
    private static int pipeWidth = 64;
    private static int pipeHeight = 512;
    private Image img;
    private boolean passed = false;
    private int velocityX = 4;

    Pipe(int x, int y, Image img) {
        this.pipeX = x;
        this.pipeY = y;
        this.img = img;
    }

    public void move(){
        pipeX -= velocityX;
    }

    public Image getImg() {
        return img;
    }

    public static int getPipeHeight() {
        return pipeHeight;
    }

    public static int getPipeWidth() {
        return pipeWidth;
    }

    public int getPipeX() {
        return pipeX;
    }

    public int getPipeY() {
        return pipeY;
    }

    public int getVelocityX() {
        return velocityX;
    }

    public boolean getPassed(){
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }

    public void setPipeX(int pipeX) {
        this.pipeX = pipeX;
    }

    public void setPipeY(int pipeY) {
        this.pipeY = pipeY;
    }

}
