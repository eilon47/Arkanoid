package levels;

import biuoop.DrawSurface;
import sprites.Block;
import sprites.Sprite;
import sprites.Velocity;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * FinalFour class.
 * Class for Final Four level in the game.
 * Created by Eilon.
 */
public class FinalFour implements LevelInformation {
    /**
     * numberOfBalls.
     * The method returns how many balls plays in this level.
     * @return number of balls.
     */
    public int numberOfBalls() {
        return 3;
    }
    /**
     * initialBallVelocities.
     * Initial velocity of each ball.
     * @return list of velocities.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<Velocity>();
        Velocity v = Velocity.fromAngleAndSpeed(320 , 240);
        Velocity v1 = Velocity.fromAngleAndSpeed(0 , 240);
        Velocity v2 = Velocity.fromAngleAndSpeed(40 , 240);
        velocities.add(v);
        velocities.add(v1);
        velocities.add(v2);
        return velocities;
    }
    /**
     * paddleSpeed.
     * Sets the speed of the paddle in this level.
     * @return speed of paddle.
     */
    public int paddleSpeed() { return 900; }
    /**
     * paddleWidth.
     * Sets the width of the paddle in this level.
     * @return width of the paddle.
     */
    public int paddleWidth() {
        return 80;
    }
    /**
     * levelName.
     * the level name will be displayed at the top of the screen.
     * @return string of the level's name.
     */
    public String levelName() { return "Final Four"; }
    /**
     * getBackground.
     * The method creates a sprite that is the background of this level.
     * Each level has different background.
     * @return a sprite with the background of the level
     */
    public Sprite getBackground() { return new Sprite() {
        @Override
        public void drawOn(DrawSurface d) {
            d.setColor(new Color(0x2C75C8));
            d.fillRectangle(0, 0, 800, 600);
            int minX = 400;
            int minY = 100;
            //Draws the clouds.
            for (int j = 0; j < 2; j++) {
                d.setColor(new Color(211, 211, 211));
                for (int i = 585 - (j * minX); i < 660 - (j * minX); i = i + 10) {
                    d.drawLine(i, 490 - (j * minY), i - 12, 600);
                }
                d.fillCircle(590 - (j * minX), 480 - (j * minY), 20);
                d.fillCircle(600 - (j * minX), 515 - (j * minY), 25);
                d.setColor(new Color(169, 169, 169));
                d.fillCircle(620 - (j * minX), 490 - (j * minY), 25);
                d.setColor(new Color(128, 128, 128));
                d.fillCircle(650 - (j * minX), 500 - (j * minY), 25);
                d.fillCircle(637 - (j * minX), 517 - (j * minY), 20);
            }
        }
        @Override
        public void timePassed(double dt) { }
    };
    }
    /**
     * blocks.
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     * @return list of blocks.
     */
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<Block>();
        double x = 20;
        double y = 300;
        double width = 50.66;
        double height = 25;
        Color[] colors = this.colorList();
        for (int i = 0; i < colors.length; i++) {
            double newX = x;
            for (int j = 0; j < 15; j++) {
                Block b = new Block((newX + (width * j)), y, width, height, colors[i], Color.black);
                if (i + 1 == colors.length) {
                    b.setNumberOnBlock(2);
                } else {
                    b.setNumberOnBlock(1);
                }
                blocks.add(b);
            }
            y = y - height;
        }

        return blocks;
    }
    /**
     * numberOfBlockToRemove.
     * Number of levels that should be removed
     * before the level is considered to be "cleared".
     * @return number of blocks.
     */
    public int numberOfBlocksToRemove() { return this.blocks().size(); }
    /**
     * colorList.
     * Creates an array of colors for the blocks.
     * @return array of colors.
     */
    public Color[] colorList() {
        Color[] colors = new Color[7];
        colors[0] = Color.cyan;
        colors[1] = Color.pink;
        colors[2] = Color.white;
        colors[3] = Color.green;
        colors[4] = Color.YELLOW;
        colors[5] = Color.RED;
        colors[6] = Color.GRAY;
        return colors;
    }
}