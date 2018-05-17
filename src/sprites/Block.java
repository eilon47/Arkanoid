package sprites;

import animations.GameLevel;
import biuoop.DrawSurface;
import collidables.Collidable;
import files.ColorsParser;
import geometry.Point;
import geometry.Rectangle;
import listeners.HitListener;
import listeners.HitNotifier;
import java.awt.Color;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Eilon.
 * ID: 308576933.
 * Class for Block.
 * The class implements Collidable and Sprite interfaces.
 * In this class the user able to set a Block - size, color, upperLeft point and more.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    //Members
    private Rectangle shape;
    private Color color;
    private int numOfHits;
    private List<HitListener> hitListeners;
    private Color stroke;
    private Image image;
    private String currentImPath;
    private Map<Integer, String> fill;
    //Constructor

    /**
     * Constructor to the block.
     *
     * @param stroke frame color.
     * @param rec    rectangle.
     * @param color  color of the block.
     */
    public Block(Rectangle rec, Color color, Color stroke) {
        this.shape = rec;
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
        this.stroke = stroke;
        this.fill = null;
    }

    /**
     * Constructor to the block.
     *
     * @param stroke    frame color.
     * @param upperLeft upper left point.
     * @param width     the width of the block.
     * @param height    the height of the block.
     * @param color     color of the block.
     */
    public Block(Point upperLeft, double width, double height, Color color, Color stroke) {
        this.shape = new Rectangle(upperLeft, width, height);
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
        this.stroke = stroke;
        this.fill = null;
    }

    /**
     * Constructor to the block.
     *
     * @param x      x value of upper left point.
     * @param y      y value of upper left point.
     * @param width  width of the block.
     * @param height height of the block.
     * @param color  color of the block.
     */
    public Block(double x, double y, double width, double height, Color color) {
        this.shape = new Rectangle(x, y, width, height);
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
        this.stroke = null;
        this.fill = null;
        this.image = null;
    }

    /**
     * Constructor to the block.
     *
     * @param x      upper left x.
     * @param y      upper left y.
     * @param width  width.
     * @param height height.
     * @param color  color.
     * @param stroke frame.
     */
    public Block(double x, double y, double width, double height, Color color, Color stroke) {
        this.shape = new Rectangle(x, y, width, height);
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
        this.stroke = stroke;
        this.fill = null;
        this.image = null;
    }

    /**
     * Constructor to the block.
     *
     * @param x      upper left x.
     * @param y      upper left y.
     * @param width  width.
     * @param height height.
     * @param fill   map that link between hit point to fill.
     */
    public Block(double x, double y, double width, double height, Map<Integer, String> fill) {
        this.shape = new Rectangle(x, y, width, height);
        this.fill = fill;
        this.hitListeners = new ArrayList<HitListener>();
        this.stroke = null;
        this.image = null;
    }

    /**
     * Constructor to the block.
     *
     * @param x      upper left x.
     * @param y      upper left y.
     * @param width  width.
     * @param height height.
     * @param fill   map that link between hit point to fill.
     * @param stroke frame color.
     */
    public Block(double x, double y, double width, double height, Map<Integer, String> fill, Color stroke) {
        this.shape = new Rectangle(x, y, width, height);
        this.stroke = stroke;
        this.fill = fill;
        this.hitListeners = new ArrayList<HitListener>();
        this.stroke = stroke;
        this.image = null;
    }

    /**
     * addToGame.
     * adds the block to game's collidable list and sprite list.
     *
     * @param g game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * setNumberOnBlock.
     * Constructor to set the number that the block will have on it.
     *
     * @param x integer number.
     */
    public void setNumberOnBlock(int x) {
        if (x < 0) {
            x = 0;
        }
        this.numOfHits = x;
    }

    /**
     * getNumOfHits.
     *
     * @return the number of hits the block has.
     */
    public int getNumOfHits() {
        return this.numOfHits;
    }

    /**
     * getCollisionRectangle.
     * Implementation of collidable interface method.
     * returns the shape of the block.
     *
     * @return the shape of the block.
     */
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }

    /**
     * hit method.
     * Implementation of collidable interface method.
     * The method gets collision point on the block and the velocity of the object that
     * hit the block. the method checks where the collision point is and returns the appropriate
     * velocity to the object. the method also notify all the hit listeners about the hit.
     *
     * @param hitter          the ball that was hit this block.
     * @param collisionPoint  the collisioin point of the object with the block.
     * @param currentVelocity the velocity of the object that hit the block.
     * @return new velocity to the object.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        Point upLeft, lowRight, upRight, lowLeft;
        //Sets the corners points of the blocks.
        upLeft = this.shape.getUpperLeft();
        lowRight = new Point(this.shape.getUpperLeft().getX() + this.shape.getWidth(),
                this.shape.getUpperLeft().getY() + this.shape.getHeight());
        lowLeft = new Point(upLeft.getX(), lowRight.getY());
        upRight = new Point(lowRight.getX(), upLeft.getY());
        //If the left side of the block got hit.
        if ((collisionPoint.getX() == upLeft.getX()) && upLeft.getY()
                <= collisionPoint.getY() && collisionPoint.getY() <= lowLeft.getY()) {
            dx = -dx;
        }
        //If the right side of the block got hit.
        if ((collisionPoint.getX() == upRight.getX()) && upRight.getY()
                <= collisionPoint.getY() && collisionPoint.getY() <= lowRight.getY()) {
            dx = -dx;
        }
        //If the upper side of the block got hit.
        if ((collisionPoint.getY() == upRight.getY()) && upLeft.getX()
                <= collisionPoint.getX() && collisionPoint.getX() <= upRight.getX()) {
            dy = -dy;
        }
        //If the lower side of the block got hit.
        if ((collisionPoint.getY() == lowRight.getY()) && lowLeft.getX()
                <= collisionPoint.getX() && collisionPoint.getX() <= lowRight.getX()) {
            dy = -dy;
        }
        this.notifyHit(hitter);
        //Sets the number of hits on block.
        this.setNumberOnBlock(this.getNumOfHits() - 1);
        return new Velocity(dx, dy);
    }

    /**
     * drawOn.
     * Implementation of Sprite interface method.
     * Draws the Block, uses fillRectangle and then drawRectangle so the block has a frame.
     * Draws the number of hits on block.
     *
     * @param d draw surface.
     */
    public void drawOn(DrawSurface d) {
        if (this.fill != null && this.fill.containsKey(this.getNumOfHits())) {
            if (this.fill.get(this.getNumOfHits()).contains("color")) {
                this.drawWithColor(d, this.fill.get(this.getNumOfHits()));
            }
            if (this.fill.get(this.getNumOfHits()).contains("image")) {
                this.drawWithImg(d, this.fill.get(this.getNumOfHits()));
            }
            //if (this.fill.get(this.getNumOfHits()).contains("empty")) - don't draw.
        } else {
            d.setColor(this.color);
            d.fillRectangle((int) this.shape.getUpperLeft().getX(), (int) this.shape.getUpperLeft().getY(),
                    (int) this.shape.getWidth(), (int) this.shape.getHeight());
        }
        if (this.stroke != null) {
            d.setColor(this.stroke);
            //Draw the frame on it.
            d.drawRectangle((int) this.shape.getUpperLeft().getX(), (int) this.shape.getUpperLeft().getY(),
                    (int) this.shape.getWidth(), (int) this.shape.getHeight());
        }
        //this.drawOnNumOfHits(d);

    }

    /**
     * draws the image inside the block.
     *
     * @param d   draw surface.
     * @param img path to the image.
     */
    public void drawWithImg(DrawSurface d, String img) {
        img = img.replace("image(", "");
        img = img.replace(")", "");
        if (this.image != null && this.currentImPath.equals(img)) {
            d.drawImage((int) this.shape.getUpperLeft().getX(), (int) this.shape.getUpperLeft().getY(), image);
        } else {
            try {
                InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(img);
                this.image = ImageIO.read(is);
                this.currentImPath = img;
                d.drawImage((int) this.shape.getUpperLeft().getX(), (int) this.shape.getUpperLeft().getY(), image);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }
    }

    /**
     * draws the block with the fill color.
     *
     * @param d           draw surface.
     * @param colorString string of color name / rgb.
     */
    public void drawWithColor(DrawSurface d, String colorString) {
        d.setColor(ColorsParser.colorFromString(colorString));
        //Draw the rectangle.
        d.fillRectangle((int) this.shape.getUpperLeft().getX(), (int) this.shape.getUpperLeft().getY(),
                (int) this.shape.getWidth(), (int) this.shape.getHeight());
    }

    /**
     * drawOnNumOfHits.
     * the method draws the number of hits the block has.
     *
     * @param d draw surface.
     */
    public void drawOnNumOfHits(DrawSurface d) {
        //x and y middle of the block.
        int x = (int) (this.shape.getUpperLeft().getX() + (this.shape.getWidth() / 2));
        int y = (int) (this.shape.getUpperLeft().getY() + (this.shape.getHeight() / 2));
        d.setColor(Color.BLACK);
        //Draw/Write the number of hits.
        if (this.numOfHits != 0) {
            d.drawText(x - 2, y + 5, Integer.toString(this.numOfHits), 20);
        } else {
            d.drawText(x - 2, y + 5, "X", 17);
        }
    }

    /**
     * TimePassed.
     * Implementation of Sprite interface method.
     *
     * @param dt 1/framesPerSecond
     */
    public void timePassed(double dt) {
    }

    /**
     * notifyHit.
     * The method notify all the hit listeners about the hit with the ball(hitter)
     *
     * @param hitter the ball that hits the blocks
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * addHitListener.
     * Add HitListener to the list so whenever there is a hit the hit listener know about it.
     *
     * @param hl HitListener.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * removeHitListener.
     * Remove listener from the list.
     *
     * @param hl HitListener.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * removeFromGame.
     * Removes the block from the given game.
     *
     * @param g game.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
        g.removeCollidable(this);
    }
}