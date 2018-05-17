package sprites;

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import listeners.HitListener;
import java.awt.Color;

/**
 * DeathBlock class.
 * DeathBlock is a Block that is not able to be drawn.
 */
public class DeathBlock extends Block {
    //Members
    private Rectangle shape;
    private Color color;
    private int numOfHits;
    private java.util.List<HitListener> hitListeners;

    /**
     * Constructor.
     *
     * @param rec   rectangle.
     * @param color color
     */
    public DeathBlock(Rectangle rec, Color color) {
        super(rec, color, color);
    }


    /**
     * Constructor.
     *
     * @param upperLeft upper left point.
     * @param width     width of the block.
     * @param height    height of the block.
     * @param color     color.
     */
    public DeathBlock(Point upperLeft, double width, double height, Color color) {
        super(upperLeft, width, height, color, color);
    }

    /**
     * Constructor.
     *
     * @param x      x of the upper left point.
     * @param y      y of the upper left point.
     * @param width  width of the block.
     * @param height height of the block.
     * @param color  color.
     */
    public DeathBlock(double x, double y, double width, double height, Color color) {
        super(x, y, width, height, color, color);
    }

    @Override
    public void drawOn(DrawSurface d) {
    }

    @Override
    public void drawOnNumOfHits(DrawSurface d) {
    }
}