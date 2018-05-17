package levels;


import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;
import sprites.Block;
import sprites.Sprite;
import sprites.Velocity;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * DirectHit class.
 * Class for Direct Hit level in the game.
 * Created by Eilon.
 */
public class DirectHit implements LevelInformation {
    /**
     * numberOfBalls.
     * The method returns how many balls plays in this level.
     *
     * @return number of balls.
     */
    public int numberOfBalls() {
        return 1;
    }

    /**
     * initialBallVelocities.
     * Initial velocity of each ball.
     *
     * @return list of velocities.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> listOfVelocities = new ArrayList<Velocity>(this.numberOfBalls());
        Velocity v = Velocity.fromAngleAndSpeed(0, 350);
        listOfVelocities.add(v);
        return listOfVelocities;
    }

    /**
     * paddleSpeed.
     * Sets the speed of the paddle in this level.
     *
     * @return speed of paddle.
     */
    public int paddleSpeed() {
        return 900;
    }

    /**
     * paddleWidth.
     * Sets the width of the paddle in this level.
     *
     * @return width of the paddle.
     */
    public int paddleWidth() {
        return 80;
    }

    /**
     * levelName.
     * the level name will be displayed at the top of the screen.
     *
     * @return string of the level's name.
     */
    public String levelName() {
        return "Direct Hit";
    }

    /**
     * getBackground.
     * The method creates a sprite that is the background of this level.
     * Each level has different background.
     *
     * @return a sprite with the background of the level
     */
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.BLACK);
                d.fillRectangle(0, 0, 800, 600);
                d.setColor(Color.BLUE);
                Line l = new Line(400, 60, 400, 310);
                Line l1 = new Line(266, 185, 533, 185);
                Point center = l1.intersectionWith(l);
                d.drawLine((int) l.start().getX(), (int) l.start().getY(),
                        (int) l.end().getX(), (int) l.end().getY());
                d.drawLine((int) l1.start().getX(), (int) l1.start().getY(),
                        (int) l1.end().getX(), (int) l1.end().getY());
                d.drawCircle((int) center.getX(), (int) center.getY(), 75);
                d.drawCircle((int) center.getX(), (int) center.getY(), 100);
                d.drawCircle((int) center.getX(), (int) center.getY(), 125);
            }

            @Override
            public void timePassed(double dt) {
            }
        };
    }

    /**
     * blocks.
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return list of blocks.
     */
    public List<Block> blocks() {
        Block b = new Block(385, 165, 30, 30, Color.RED, Color.black);
        b.setNumberOnBlock(1);
        List<Block> blockList = new ArrayList<Block>();
        blockList.add(b);
        return blockList;
    }

    /**
     * numberOfBlockToRemove.
     * Number of levels that should be removed
     * before the level is considered to be "cleared".
     *
     * @return number of blocks.
     */
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }
}