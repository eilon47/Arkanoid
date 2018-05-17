package collidables;

import geometry.Point;

/**
 * Created by Eilon.
 * ID: 308576933.
 * Collision info.
 * Holds the information about the collision, what kind of object and collision point.
 */
public class CollisionInfo {
    //Members
    private Point colPoint;
    private Collidable colObject;

    /**
     * CollisionInfo constructor.
     *
     * @param p collision point.
     * @param c collidable object.
     */
    public CollisionInfo(Point p, Collidable c) {
        this.colPoint = p;
        this.colObject = c;
    }

    /**
     * collisionPoint.
     *
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.colPoint;
    }

    /**
     * collisionObject.
     *
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.colObject;
    }
}