package sprites;

import biuoop.DrawSurface;

/**
 * Created by Eilon.
 * ID: 308576933.
 * Sprite interface.
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d surface.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     * @param dt 1/framesPerSecond
     */
    void timePassed(double dt);
}