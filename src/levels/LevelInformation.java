package levels;

import sprites.Block;
import sprites.Sprite;
import sprites.Velocity;

import java.util.List;

/**
 *Class for LevelInformation interface.
 */
public interface LevelInformation {
    /**
     * numberOfBalls.
     * The method returns how many balls plays in this level.
     *
     * @return number of balls.
     */
    int numberOfBalls();

    /**
     * initialBallVelocities.
     * Initial velocity of each ball.
     *
     * @return list of velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * paddleSpeed.
     * Sets the speed of the paddle in this level.
     *
     * @return speed of paddle.
     */
    int paddleSpeed();

    /**
     * paddleWidth.
     * Sets the width of the paddle in this level.
     *
     * @return width of the paddle.
     */
    int paddleWidth();

    /**
     * levelName.
     * the level name will be displayed at the top of the screen.
     *
     * @return string of the level's name.
     */
    String levelName();

    /**
     * getBackground.
     * The method creates a sprite that is the background of this level.
     * Each level has different background.
     *
     * @return a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * blocks.
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return list of blocks.
     */
    List<Block> blocks();

    /**
     * numberOfBlockToRemove.
     * Number of levels that should be removed
     * before the level is considered to be "cleared".
     *
     * @return number of blocks.
     */
    int numberOfBlocksToRemove();
}