package sprites;

import geometry.Point;

/**
 * Created by Eilon.
 * ID: 308576933.
 * collidables.Velocity class.
 * collidables.Velocity specifies the change in position on the `x` and the `y` axis.
 */
public class Velocity {
    //Members.
    private double dx;
    private double dy;

    /**
     * Constructor to the velocity.
     *
     * @param dx the different the user wants on the x axis.
     * @param dy the different the user wants on the y axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * applyToPoint.
     * Take a point with position (x,y) and return a new point
     * with position (x+dx, y+dy).
     *
     * @param p the current point of the item.
     * @return the point after the change.
     */
    public Point applyToPoint(Point p) {
        return new Point((p.getX() + this.dx), (p.getY() + this.dy));
    }

    /**
     * fromAngleAndSpeed.
     * The method calculate the velocity by angle and speed given.
     *
     * @param angle the angle the item should move on.
     * @param speed the speed of the item.
     * @return the new velocity determined by speed and angle.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.sin(Math.toRadians(angle)) * speed;
        double dy = -Math.cos(Math.toRadians(angle)) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * bounceOffVerticalBorder.
     * Change the direction of the item which runs on the x axis.
     */
    public void bounceOffVerticalBorder() {
        this.dx = -this.dx;
    }

    /**
     * bounceOffHorizontalBorder.
     * Change the direction of the item which runs on the y axis.
     */
    public void bounceOffHorizontalBorder() {
        this.dy = -this.dy;
    }

    /**
     * Gives the dx.
     *
     * @return dx change of the item.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Gives the dy.
     *
     * @return dy change of the item.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * calculates the speed from dx and dy.
     * @return speed of ball.
     */
    public double getSpeedFromVelocity() {
        return Math.sqrt((this.dx * this.dx) + (this.dy * this.dy));
    }
}