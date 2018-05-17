package listeners;

import animations.GameLevel;
import sprites.Ball;
import sprites.Block;
import sprites.Counter;

/**
 * BallRemover class.
 * BallRemover class implements HitListener interface.
 * BallRemover is in charge of removing balls from the game, as well as keeping count
 * of the number of balls that remain.
 * Created by Eilon.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * Constructor.
     *
     * @param game  gamelevel.
     * @param balls counter of balls.
     */
    public BallRemover(GameLevel game, Counter balls) {
        this.game = game;
        this.remainingBalls = balls;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * BallRemover is a block whenever ball hits it , the ball is gone.
     *
     * @param beingHit the block that was hit.
     * @param hitter   the ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.remainingBalls.decrease(1);
        hitter.removeFromGame(this.game);
    }
}