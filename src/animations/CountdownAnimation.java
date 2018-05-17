package animations;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import sprites.SpriteCollection;
import java.awt.Color;

/**
 * CountdownAnimation.
 * Animation for counting down before the game starts.
 * Created by Eilon.
 */
public class CountdownAnimation implements Animation {
    //Members.
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;

    /**
     * Constructor.
     *
     * @param numOfSeconds number of second the animation should run.
     * @param countFrom    a number to cound from.
     * @param gameScreen   sprite collection of the objects on screen.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
    }

    /**
     * doOneFrame.
     * does one frame of the countdown.
     * @param dt 1 / framespersecond
     * @param d draw surface.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.BLACK);
        d.drawText(365, 300, Integer.toString(this.countFrom), 160);
        d.setColor(Color.RED);
        d.drawText(365, 300, Integer.toString(this.countFrom), 150);
        Sleeper sleeper = new Sleeper();
        long timing = (long) ((this.numOfSeconds / 4) * 1000);
        sleeper.sleepFor(timing);
        this.countFrom--;
    }

    /**
     * shouldStop.
     *
     * @return true or false.
     */
    public boolean shouldStop() {
        if (this.countFrom < 0) {
            return true;
        }
        return false;
    }
}