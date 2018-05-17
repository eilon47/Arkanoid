package sprites;

import biuoop.DrawSurface;



/**
 * ScoreIndicator class.
 * The indicator for the score counter.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * Constructor.
     *
     * @param score counter for the score.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    /**
     * getCunter.
     *
     * @return returns indicator's counter.
     */
    public Counter getCounter() {
        return this.score;
    }

    /**
     * getValue.
     *
     * @return returns indicator's counter value
     */
    public int getValue() {
        return this.score.getValue();
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d surface.
     */
    public void drawOn(DrawSurface d) {
    }

    /**
     * notify the sprite that time has passed.
     * @param dt 1/framesPerSecond
     */
    public void timePassed(double dt) {
    }
}