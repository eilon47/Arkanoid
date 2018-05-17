package sprites;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * IndicatorsBlock class.
 * IndicatorsBlock gets indicators and block and draws them together.
 */
public class IndicatorsBlock implements Sprite {
    //Members.
    private BoundaryBlock block;
    private LiveIndicator liveIndicator;
    private ScoreIndicator scoreIndicator;
    private String levelName;

    /**
     * Constructor.
     *
     * @param b         block.
     * @param live      live indicator.
     * @param score     score indicator.
     * @param levelName level's namme.
     */
    public IndicatorsBlock(BoundaryBlock b, LiveIndicator live, ScoreIndicator score, String levelName) {
        this.block = b;
        this.liveIndicator = live;
        this.scoreIndicator = score;
        this.levelName = levelName;
    }

    /**
     * draw the block to the screen and draws the indicators on it..
     *
     * @param d surface.
     */
    public void drawOn(DrawSurface d) {
        this.block.drawOn(d);
        d.setColor(Color.BLACK);
        int width = (int) this.block.getCollisionRectangle().getWidth();
        d.drawText((int) (0.05 * width), 28,
                "Lives: " + Integer.toString(this.liveIndicator.getValue()), 20);
        d.drawText((int) (0.35 * width), 28,
                "Score: " + Integer.toString(this.scoreIndicator.getValue()), 20);
        d.drawText((int) (0.60 * width), 28, "Level Name: " + this.levelName, 20);

    }

    /**
     * notify the sprite that time has passed.
     * @param dt 1/framesPerSecond
     */
    public void timePassed(double dt) {
    }
}