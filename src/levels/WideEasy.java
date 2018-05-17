package levels;


import biuoop.DrawSurface;
import geometry.Point;
import sprites.Block;
import sprites.Sprite;
import sprites.Velocity;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * WideEasy class.
 * Class for Wide Easy level in the game.
 * Created by Eilon.
 */
public class WideEasy implements LevelInformation {
    /**
     * numberOfBalls.
     * The method returns how many balls plays in this level.
     *
     * @return number of balls.
     */
    public int numberOfBalls() {
        return 10;
    }

    /**
     * initialBallVelocities.
     * Initial velocity of each ball.
     *
     * @return list of velocities.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<Velocity>();
        for (int i = 0; i < (this.numberOfBalls() / 2); i++) {
            int angle = (i * 10) + 300;
            int angle2 = (i * 10) + 30;
            Velocity v = Velocity.fromAngleAndSpeed(angle, 350);
            Velocity v2 = Velocity.fromAngleAndSpeed(angle2, 350);
            velocities.add(v);
            velocities.add(v2);
        }
        return velocities;
    }

    /**
     * paddleSpeed.
     * Sets the speed of the paddle in this level.
     *
     * @return speed of paddle.
     */
    public int paddleSpeed() {
        return 300;
    }

    /**
     * paddleWidth.
     * Sets the width of the paddle in this level.
     *
     * @return width of the paddle.
     */
    public int paddleWidth() {
        return 550;
    }

    /**
     * levelName.
     * the level name will be displayed at the top of the screen.
     *
     * @return string of the level's name.
     */
    public String levelName() {
        return "Wide Easy";
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
                d.setColor(Color.WHITE);
                d.fillRectangle(0, 0, 800, 600);
                Point center = new Point(150, 150);
                d.setColor(new Color(255, 235, 153));
                for (int i = 0; i < 100; i++) {
                    d.drawLine((int) center.getX(), (int) center.getY(), (20 + (10 * i)), 300);
                }
                d.fillCircle((int) center.getX(), (int) center.getY(), 65);
                d.setColor(new Color(204, 163, 0));
                d.fillCircle((int) center.getX(), (int) center.getY(), 55);
                d.setColor(new Color(255, 226, 94));
                d.fillCircle((int) center.getX(), (int) center.getY(), 45);
            }

            @Override
            public void timePassed(double dt) { }
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
        List<Block> blocks = new ArrayList<Block>();
        double x = 20;
        double y = 300;
        double width = 50.66;
        double height = 25;
        Color[] colors = this.colorList();
        for (int i = 0; i < 15; i++) {
            Block b = new Block((x + (width * i)), y, width, height, colors[i], Color.black);
            b.setNumberOnBlock(1);
            blocks.add(b);
        }
        return blocks;
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

    /**
     * colorList.
     * Creates an array of colors for the blocks.
     *
     * @return array of colors.
     */
    public Color[] colorList() {
        Color[] colors = new Color[15];
        colors[0] = Color.RED;
        colors[1] = Color.RED;
        colors[2] = Color.ORANGE;
        colors[3] = Color.ORANGE;
        colors[4] = Color.YELLOW;
        colors[5] = Color.YELLOW;
        colors[6] = Color.GREEN;
        colors[7] = Color.GREEN;
        colors[8] = Color.GREEN;
        colors[9] = Color.BLUE;
        colors[10] = Color.BLUE;
        colors[11] = Color.PINK;
        colors[12] = Color.PINK;
        colors[13] = Color.CYAN;
        colors[14] = Color.CYAN;
        return colors;
    }
}