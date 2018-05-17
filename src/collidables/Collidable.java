package collidables;
import geometry.Point;
import geometry.Rectangle;
import sprites.Ball;
import sprites.Velocity;

/**
 * Created by Eilon.
 * ID: 308576933.
 * Collidable interface.
 */
public interface Collidable {
    /**
     * getCollisionRectangle.
     *
     * @return Return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param hitter          the balls that hits the collidable.
     * @param collisionPoint  the point the collision happens
     * @param currentVelocity given velocity of the object is collided with.
     * @return new velocity expected after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}