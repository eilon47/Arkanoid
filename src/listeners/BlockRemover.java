package listeners;

import animations.GameLevel;
import sprites.Ball;
import sprites.Block;
import sprites.Counter;

/**
 * BlockRemover class.
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 * Created by Eilon.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * Constructor.
     *
     * @param game          game level.
     * @param removedBlocks Counter of blocks.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * This method whenever there is a hit checks the number of hits on the block
     * if the number is 1 which means the block shoould be removed.
     *
     * @param beingHit the block that was hit.
     * @param hitter   the ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getNumOfHits() == 1) {
            this.remainingBlocks.decrease(1);
            beingHit.removeFromGame(this.game);

        }
    }
}