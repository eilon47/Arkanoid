package listeners;

import sprites.Ball;
import sprites.Block;

/**
 * Class For HitListener interface.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit the block that was hit.
     * @param hitter   the ball.
     */
    void hitEvent(Block beingHit, Ball hitter);
}