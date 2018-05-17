package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import sprites.Counter;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;

import java.io.IOException;
import java.io.InputStream;

/**
 * EndScreen class.
 * Animation for the game when it's over.
 * Created by Eilon.
 */
public class EndScreen implements Animation {
    //Members.
    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter score;
    private boolean winOrLose;
    private Image winImg;
    private Image loseImg;

    /**
     * Constructor.
     *
     * @param k         keyboard.
     * @param winOrLose true or false if the player won the game.
     * @param score     the score the player got.
     */
    public EndScreen(KeyboardSensor k, boolean winOrLose, Counter score) {
        this.keyboard = k;
        this.stop = false;
        this.winOrLose = winOrLose;
        this.score = score;
        this.loseImg = null;
        this.winImg = null;
    }

    /**
     * doOneFrame.
     *
     * @param dt 1 / framespersecond
     * @param d draw surface.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        String scoreString = "Your score is: " + Integer.toString(this.score.getValue());
        int width = d.getWidth();
        int height = d.getHeight();
        d.setColor(Color.white);
        d.fillRectangle(0, 0, width, height);
        d.setColor(Color.BLACK);
        d.drawText((int) (width * 0.3), (int) (height * 0.8), "press space to continue", 32);
        if (winOrLose) {
            if (this.winImg == null) {
                InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("screens_images/youwin.jpg");
                try {
                    this.winImg = ImageIO.read(is);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            d.drawImage((int) (width * 0.2), (int) (height * 0.2) , this.winImg);
            d.drawText((int) (width * 0.25), (int) (height * 0.6), scoreString, 48);
        } else {
            if (this.loseImg == null) {
                InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("screens_images/youlose.png");

                try {
                    this.loseImg = ImageIO.read(is);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            d.drawImage((int) (width * 0.18), (int) (height * 0.08) , this.loseImg);
            d.drawText((int) (width * 0.25), (int) (height * 0.7), scoreString, 48);
        }
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }

    }

    /**
     * shouldStop.
     *
     * @return true or false.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}