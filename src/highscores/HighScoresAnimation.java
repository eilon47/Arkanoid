package highscores;

import animations.Animation;
import biuoop.DrawSurface;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

/**
 *Class for the animation of highscore table.
 */
public class HighScoresAnimation implements Animation {
    //Members.
    private HighScoresTable hst;
    private Boolean stop;
    private Image podium;

    /**
     * Constructor to highscore table animation.
     *
     * @param scores highscore table.
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.stop = false;
        this.hst = scores;
        this.podium = null;
    }

    /**
     * doOneFrame.
     * Method to do one frame of the animation.
     *
     * @param dt 1/framesPerSecond.
     * @param d  draw surface.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        //background.
        d.setColor(Color.white);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        int width = d.getWidth();
        int height = d.getHeight();
        //headline.
        d.setColor(Color.BLACK);
        d.drawText((int) (width * 0.3), (int) (height * 0.15), "HighScores!", 64);
        //image.
        this.drawPodium(d);
        //Draws the scores.
        if (this.hst.getHighScores().size() > 0) {
            //First
            d.drawText((int) (width * 0.45), (int) (height * 0.27),
                    this.hst.getHighScores().get(0).getName(), 25);
            d.drawText((int) (width * 0.45), (int) (height * 0.31),
                    Integer.toString(this.hst.getHighScores().get(0).getScore()), 25);
            if (this.hst.getHighScores().size() > 1) {
                d.drawText((int) (width * 0.28), (int) (height * 0.35),
                        this.hst.getHighScores().get(1).getName(), 25);
                d.drawText((int) (width * 0.28), (int) (height * 0.39),
                        Integer.toString(this.hst.getHighScores().get(1).getScore()), 25);
                if (this.hst.getHighScores().size() > 2) {
                    d.drawText((int) (width * 0.62), (int) (height * 0.40),
                            this.hst.getHighScores().get(2).getName(), 25);
                    d.drawText((int) (width * 0.62), (int) (height * 0.44),
                            Integer.toString(this.hst.getHighScores().get(2).getScore()), 25);
                }
            }
            //if there are more than 3 names in the highscore table write them under the podium.
            if (this.hst.getHighScores().size() > 3) {
                d.drawText((int) (width * 0.3), (int) (height * 0.67), "#", 30);
                d.drawText((int) (width * 0.38), (int) (height * 0.67), "Player", 30);
                d.drawText((int) (width * 0.63), (int) (height * 0.67), "Score", 30);
                for (int i = 4; i <= this.hst.getHighScores().size(); i++) {
                    d.setColor(Color.BLACK);
                    d.drawText((int) (width * 0.30), (int) ((height + (65 * i)) * 0.5),
                            i + ". ", 25);
                    d.drawText((int) (width * 0.38), (int) ((height + (65 * i)) * 0.5),
                            this.hst.getHighScores().get(i - 1).getName(), 25);
                    d.drawText((int) (width * 0.63), (int) ((height + (65 * i)) * 0.5),
                            Integer.toString(this.hst.getHighScores().get(i - 1).getScore()), 25);
                }
            }
        }
        d.drawText((int) (width * 0.3), (int) (height * 0.9), "press space to continue", 32);
    }

    /**
     * draws the podium image.
     *
     * @param d draw surface.
     */
    public void drawPodium(DrawSurface d) {
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("screens_images/podium.jpg");

        if (this.podium == null) {
            try {
                this.podium = ImageIO.read(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (this.podium != null) {
            d.drawImage((int) (d.getWidth() * 0.2), (int) (d.getHeight() * 0.25), this.podium);
        }
    }

    /**
     * shouldStop.
     * The method returns false if the animation should not stop and
     * true if it does.
     *
     * @return true or false.
     */
    public boolean shouldStop() {
        if (this.stop) {
            this.stop = false;
            return true;
        }
        return false;
    }
}