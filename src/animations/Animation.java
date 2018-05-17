package animations;
import biuoop.DrawSurface;
/**
 * Animation interface.
 * Created by Eilon.
 */
public interface Animation {
    /**
     * doOneFrame.
     * Method to do one frame of the animation.
     *
     * @param dt 1 / framesPerSecond.
     * @param d  draw surface.
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * shouldStop.
     * The method returns false if the animation should not stop and
     * true if it does.
     *
     * @return true or false.
     */
    boolean shouldStop();
}