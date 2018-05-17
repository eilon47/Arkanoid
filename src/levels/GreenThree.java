package levels;

import biuoop.DrawSurface;
import sprites.Block;
import sprites.Sprite;
import sprites.Velocity;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * GreenThree class.
 * Class for Green Three level in the game.
 * Created by Eilon.
 */
public class GreenThree implements LevelInformation {
    /**
     * numberOfBalls.
     * The method returns how many balls plays in this level.
     *
     * @return number of balls.
     */
    public int numberOfBalls() {
        return 2;
    }

    /**
     * initialBallVelocities.
     * Initial velocity of each ball.
     *
     * @return list of velocities.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<Velocity>();
        Velocity v = Velocity.fromAngleAndSpeed(40, 240);
        Velocity v2 = Velocity.fromAngleAndSpeed(320, 240);
        velocities.add(v);
        velocities.add(v2);
        return velocities;
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
        return "Green 3";
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
                int x = 100;
                int y = 210;
                d.setColor(new Color(9, 136, 37));
                d.fillRectangle(0, 0, 800, 600);
                d.setColor(new Color(89, 89, 89));
                d.fillRectangle(x, y, 10, 500);
                d.setColor(Color.darkGray);
                d.fillRectangle(x - 10, y + 170, 30, 500);
                d.setColor(Color.BLACK);
                d.fillRectangle(x - 40, y + 220, 90, 500);
                d.setColor(new Color(160, 130, 25));
                d.fillCircle(x + 5, y - 5, 15);
                d.setColor(new Color(160, 59, 54));
                d.fillCircle(x + 5, y - 5, 9);
                d.setColor(new Color(251, 250, 250));
                d.fillCircle(x + 5, y - 5, 3);
                x = x - 33;
                y = y + 225;
                int newX = x;
                int width = 9;
                int height = 20;
                d.setColor(Color.WHITE);
                for (int i = 0; i < 7; i++) {
                    for (int j = 0; j < 5; j++) {
                        d.fillRectangle(newX, y, width, height);
                        newX = newX + width + 8;
                    }
                    newX = x;
                    y = y + height + 5;
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
     *
     * @return list of blocks.
     */
    public List<Block> blocks() {
        int width = 50;
        int height = 30;
        double x = 800 - width - 20;
        double y = 300;
        Color[] colors = this.colorList();
        List<Block> blocks = new ArrayList<Block>();
        int i, j, k;
        k = 6;
        for (i = 0; i < 5; i++) {
            for (j = 0; j < k; j++) {
                double newX = x - j * width;
                Block b = new Block(newX, y, width, height, colors[i], Color.black);
                if (i + 1 == 5) {
                    b.setNumberOnBlock(2);
                } else {
                    b.setNumberOnBlock(1);
                }
                blocks.add(b);
            }
            y = y - height;
            k++;
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
        Color[] colorsArr = new Color[5];
        colorsArr[0] = Color.WHITE;
        colorsArr[1] = Color.BLUE;
        colorsArr[2] = Color.YELLOW;
        colorsArr[3] = Color.RED;
        colorsArr[4] = Color.GRAY;

        return colorsArr;
    }
}