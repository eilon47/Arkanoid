package sprites;

import biuoop.DrawSurface;


/**
 * LiveIndicator class.
 * The indicator for the live counter.
 */
public class LiveIndicator implements Sprite {
    private Counter lives;

    /**
     * Constructor.
     *
     * @param lives counter for lives.
     */
    public LiveIndicator(Counter lives) {
        this.lives = lives;

    }

    /**
     * getCunter.
     *
     * @return returns indicator's counter.
     */
    public Counter getCounter() {
        return this.lives;
    }

    /**
     * getValue.
     *
     * @return returns indicator's counter value
     */
    public int getValue() {
        return this.lives.getValue();
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