package sprites;

import animations.GameLevel;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collidables.Collidable;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;
import java.util.Random;

/**
 * Created by Eilon.
 * ID: 308576933.
 * Class for Paddle.
 * Paddle class implements Sprite and Collidable interfaces.
 */
public class Paddle implements Sprite, Collidable {
    //Members.
    private biuoop.KeyboardSensor keyboard;
    private Rectangle movingBlock;
    private Point upperLeft;
    private Point centerPaddlePoint;
    private double width;
    private double height;
    private Color color;
    private double screenWidth;
    private int speed;

    /**
     * Cunstroctor for Paddle.
     *
     * @param speed          speed of the paddle.
     * @param keyboardSensor keyboard from the game.
     * @param g              game.
     * @param width          width of the paddle.
     * @param height         height of the paddle.
     * @param color          color of paddle.
     */
    public Paddle(GameLevel g, KeyboardSensor keyboardSensor, double width, double height, Color color, int speed) {
        this.keyboard = keyboardSensor;
        this.screenWidth = g.getGuiWidth() - 20;
        this.centerPaddlePoint = new Point(g.getGuiWidth() / 2, g.getGuiHeight() - 30);
        this.height = height;
        this.width = width;
        this.speed = speed;
        this.upperLeft = new Point(centerPaddlePoint.getX() - (width / 2),
                centerPaddlePoint.getY() - (height / 2));
        this.movingBlock = new Rectangle(this.upperLeft, this.width, this.height);
        this.color = color;
    }

    /**
     * addToGame.
     * Add this paddle to the game.
     *
     * @param g game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * getPaddleWidth.
     *
     * @return paddle's width.
     */
    public double getPaddleWidth() {
        return this.width;
    }

    /**
     * getPaddleHeight.
     *
     * @return paddle's height.
     */
    public double getPaddleHeight() {
        return this.height;
    }

    /**
     * getCenterPaddlePoint.
     *
     * @return paddle's center point.
     */
    public Point getCenterPaddlePoint() {
        return this.centerPaddlePoint;
    }

    /**
     * getUpperLeft.
     *
     * @return paddle's upper left point.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * moveLeft.
     * moves the paddle to the left.
     * @param dt 1/framesPerSecond
     */
    public void moveLeft(double dt) {
        this.upperLeft = new Point(this.upperLeft.getX() - (this.speed * dt), this.upperLeft.getY());
        if (this.upperLeft.getX() <= 20) {
            this.upperLeft = new Point(20, this.upperLeft.getY());
        }
        this.movingBlock = new Rectangle(this.upperLeft, this.getPaddleWidth(), this.getPaddleHeight());
    }

    /**
     * moveRight.
     * moves the paddle to the right.
     * @param dt 1/framesPerSecond
     */
    public void moveRight(double dt) {
        this.upperLeft = new Point(this.upperLeft.getX() + (this.speed * dt), this.upperLeft.getY());
        if (this.upperLeft.getX() >= this.screenWidth - this.getPaddleWidth()) {
            this.upperLeft = new Point(this.screenWidth - this.getPaddleWidth(), this.upperLeft.getY());
        }
        this.movingBlock = new Rectangle(this.upperLeft, this.getPaddleWidth(), this.getPaddleHeight());
    }

    /**
     * TimePassed.
     * Implementation of Sprite interface method.
     * Calls to moveLeft or moveRight.
     * @param dt 1/framesPerSecond
     */
    public void timePassed(double dt) {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft(dt);
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight(dt);
        }
    }

    /**
     * Draws the paddle on the given surface.
     * Implementation of Sprite interface method.
     *
     * @param d where to draw the ball.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.movingBlock.getUpperLeft().getX(), (int) this.movingBlock.getUpperLeft().getY(),
                (int) this.movingBlock.getWidth(), (int) this.movingBlock.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.movingBlock.getUpperLeft().getX(), (int) this.movingBlock.getUpperLeft().getY(),
                (int) this.movingBlock.getWidth(), (int) this.movingBlock.getHeight());

    }

    /**
     * getCollisionRectangle.
     * Implementation of collidable interface method.
     * returns the shape of the paddle.
     *
     * @return the shape of the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return this.movingBlock;
    }

    /**
     * hit method.
     * Implementation of collidable interface method.
     * The method gets collision point on the paddle and the velocity of the object that
     * hit the paddle. the method checks where the collision point is and returns the appropriate
     * velocity to the object.
     *
     * @param hitter          the ball which hits the paddle.
     * @param collisionPoint  the collisioin point of the object with the block.
     * @param currentVelocity the velocity of the object that hit the block.
     * @return new velocity to the object.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        double ballSpeed = currentVelocity.getSpeedFromVelocity();
        //Sets the corners of the block.
        Point upLeft, lowRight, upRight, lowLeft;
        upLeft = this.getUpperLeft();
        lowRight = new Point(this.getUpperLeft().getX() + this.getPaddleWidth(),
                this.getUpperLeft().getY() + this.getPaddleHeight());
        lowLeft = new Point(upLeft.getX(), lowRight.getY());
        upRight = new Point(lowRight.getX(), upLeft.getY());
        //If the collision point is on the top of the paddle.
        //this.color = this.generateRandomColor();
        if ((collisionPoint.getY() == upLeft.getY())) {
            //Define different 21 "x's" on the line.
            double[] xValues = new double[21];
            for (int i = 0; i < 21; i++) {
                xValues[i] = this.getUpperLeft().getX() + (i * (this.width / 20));
            }
            //Checks between which x's the collision point is and returns appropriate angle.
            for (int i = 0; i + 1 < 21; i++) {
                if (xValues[i] <= collisionPoint.getX() && collisionPoint.getX() <= xValues[i + 1]) {
                    return Velocity.fromAngleAndSpeed((280 + (i * 8)), ballSpeed);
                }
            }
        }
        //If the collisioin point is on the sides of the paddle.
        if ((collisionPoint.getX() == upLeft.getX()) && upLeft.getY()
                <= collisionPoint.getY() && collisionPoint.getY() <= lowLeft.getY()) {
            dx = -dx;

        }
        if ((collisionPoint.getX() == upRight.getX()) && upRight.getY()
                <= collisionPoint.getY() && collisionPoint.getY() <= lowRight.getY()) {
            dx = -dx;
        }
        return new Velocity(dx, dy);
    }

    /**
     * generateRandomColor.
     * The method get random numbers from 0 - 255 for red, green and blue.
     *
     * @return random color.
     */
    public Color generateRandomColor() {
        Random rand = new Random();
        int r = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);
        //If two of the three are equals try again recursively, unwanted varieties.
        if (r != g && g != b && r != b) {
            return new Color(r, g, b);
        } else {
            return generateRandomColor();
        }
    }
}