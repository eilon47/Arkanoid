package sprites;
import animations.GameLevel;
import collidables.CollisionInfo;
import geometry.Point;
import geometry.Line;
import biuoop.DrawSurface;
import collidables.GameEnvironment;
import geometry.Rectangle;
import java.awt.Color;


/**
 * Created by Eilon.
 * ID: 308576933.
 * Class for ball.
 * Ball class implements Sprite interface.
 * In this class the user able to set a ball - size, color, start point, velocity and borders.
 */
public class Ball implements Sprite {
    //Members.
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity v;
    private Boundaries limits;
    private GameEnvironment game;

    /**
     * Constructor to ball.
     *
     * @param center the first point of the ball, where the ball is made.
     * @param r      the radius of the ball.
     * @param color  color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = new Point(center.getX(), center.getY());
        this.r = Math.abs(r);
        this.color = color;
        this.v = new Velocity(0, 0);
    }

    /**
     * Constructor to ball.
     *
     * @param x     x of center point of the ball.
     * @param y     y of center point of the ball.
     * @param r     the radius of the ball.
     * @param color color of the ball.
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.r = Math.abs(r);
        this.color = color;
        this.v = new Velocity(0, 0);
    }

    /**
     * Constructor to the velocity of the ball.
     *
     * @param velocity velocity type.
     */
    public void setVelocity(Velocity velocity) {
        this.v = velocity;
    }

    /**
     * Constructor to the velocity of the ball.
     *
     * @param dx the change on the x.
     * @param dy the change on the y.
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * addToGame.
     * adds the ball to the game's sprite array.
     *
     * @param g game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * The method sets the game environment of the ball.
     *
     * @param gameEnvi the game environment.
     */
    public void setGameEnvironment(GameEnvironment gameEnvi) {
        this.game = gameEnvi;
    }

    /**
     * Accessor.
     *
     * @return the x of the center point.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Accessor.
     *
     * @return the y of the center point.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Accessor.
     *
     * @return the radius of the ball.
     */
    public int getSize() {
        return this.r;
    }

    /**
     * Accessor.
     *
     * @return the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Accessor to the velocity of the ball.
     *
     * @return velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * chekcCenterAndBorders.
     * The method sets the border of the ball and checks the center is in the boundaries.
     *
     * @param p1 first point to the boundaries.
     * @param p2 second point to the boundaries.
     */
    public void checkCenterAndBorders(Point p1, Point p2) {
        this.limits = new Boundaries(p1, p2);
        this.center = this.checkCenter();
    }

    /**
     * The method checks if the center is in logical place on board, and if it doesn't
     * it's change its location to a right location.
     *
     * @return location point.
     */
    public Point checkCenter() {
        if (this.getY() > this.limits.getLowY() - this.r) {
            this.center = new Point(this.getX(), (this.limits.getLowY() - this.r));
        }
        if (this.getY() < this.limits.getHighY() + this.r) {
            this.center = new Point(this.getX(), (this.limits.getHighY() + this.r));
        }
        if (this.getX() > (this.limits.getRightX() - this.r)) {
            this.center = new Point((this.limits.getRightX() - this.r), this.getY());
        }
        if (this.getX() < this.limits.getLeftX() + this.r) {
            this.center = new Point(this.limits.getLeftX() + this.r, this.getY());
        }
        return this.center;
    }

    /**
     * moveOneStep.
     * The function creates a line from the center point of the ball and the velocity,
     * then it is checking if there are any collision points with any of the collidables, if
     * there are no collision points the ball keep moving, else it checks where the hit was
     * changes it's velocity and moves the center of the ball to the closest point to the c
     * collision point.
     *
     * @param dt 1/framesPerSecond
     */
    public void moveOneStep(double dt) {
        //Line made by the center of the ball and it's velocity.
        Velocity newV = new Velocity((this.v.getDx() * dt), (this.v.getDy() * dt));
        Line trajectory = new Line(this.center, newV.applyToPoint(this.center));
        //Gets if there are any collisions.
        CollisionInfo collisionInfo = this.game.getClosestCollision(trajectory);
        if (collisionInfo == null) {
            //If there are no collisions the balls moves to it's next step.
            this.center = newV.applyToPoint(this.center);
        } else {
            //Gets the collision object.
            Rectangle rectangle = collisionInfo.collisionObject().getCollisionRectangle();
            //Checks in which part of the object the ball collided.
            //Left line of the block.
            if (collisionInfo.collisionPoint().getX() == rectangle.getUpperLeft().getX()) {
                this.center = new Point(rectangle.getUpperLeft().getX() - this.r, this.center.getY());
            }
            //Right line of the block.
            if (collisionInfo.collisionPoint().getX() == (rectangle.getUpperLeft().getX() + rectangle.getWidth())) {
                this.center = new Point(rectangle.getUpperLeft().getX()
                        + this.r + rectangle.getWidth(), this.center.getY());
            }
            //Upper line of the block.
            if (collisionInfo.collisionPoint().getY() == rectangle.getUpperLeft().getY()) {
                this.center = new Point(this.center.getX(), rectangle.getUpperLeft().getY() - this.r);
            }
            //Lower line of the block.
            if (collisionInfo.collisionPoint().getY() == rectangle.getUpperLeft().getY() + rectangle.getHeight()) {
                this.center = new Point(this.center.getX(), rectangle.getUpperLeft().getY()
                        + this.r + rectangle.getHeight());
            }
            //Sets new velocity.
            this.v = collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), this.v);
        }
    }

    /**
     * TimePassed.
     * Implementation of Sprite interface method.
     * Calls to moveOneStep.
     *
     * @param dt 1/framesPerSecond
     */
    public void timePassed(double dt) {
        this.moveOneStep(dt);
    }

    /**
     * Draws the ball on the given surface.
     * Implementation of Sprite interface method.
     *
     * @param surface where to draw the ball.
     */
    public void drawOn(DrawSurface surface) {
        //Set the color of the ball.
        surface.setColor(this.color);
        //First location and size.
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * removeFromGame.
     * the method removes this ball from the given game.
     *
     * @param g game.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}