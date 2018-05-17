package collidables;

import geometry.Line;
import geometry.Point;
import java.util.ArrayList;
/**
 * Created by Eilon.
 * ID: 308576933.
 * GameEnvironment class.
 * Holds all the collidables collidables in game and can give the closest collision point with a line.
 */
public class GameEnvironment {
    //Members
    private ArrayList<Collidable> collidablesList;

    /**
     * Constructor for GameEnvironment.
     */
    public GameEnvironment() {
        this.collidablesList = new ArrayList<Collidable>();
    }

    /**
     * add the given collidable to the environment.
     *
     * @param c collidable object.
     */
    public void addCollidable(Collidable c) {
        this.collidablesList.add(c);
    }
    /**
     * remove the given collidable from the environment.
     *
     * @param c collidable object.
     */
    public void removeCollidable(Collidable c) {
        this.collidablesList.remove(c);
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory the line we check.
     * @return collision information.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        //If there are no collidables in the game.
        if (this.collidablesList.isEmpty()) {
            return null;
        }
        Point colPoint = trajectory.closestIntersectionToStartOfLine(
                collidablesList.get(0).getCollisionRectangle());
        Collidable colObject = collidablesList.get(0);
        for (int i = 0; i < collidablesList.size(); i++) {
            Point iPoint = trajectory.closestIntersectionToStartOfLine(
                    collidablesList.get(i).getCollisionRectangle());
            //if the first point is null assign the next one in it.
            if (colPoint == null) {
                colPoint = iPoint;
                colObject = collidablesList.get(i);
                //if both points are not null check which one is closer to the start of the line.
            } else if (iPoint != null) {
                if (trajectory.start().distance(colPoint) > trajectory.start().distance(iPoint)) {
                    colPoint = iPoint;
                    colObject = collidablesList.get(i);
                }
            }
        }
        //if there isn't any collision.
        if (colPoint == null) {
            return null;
        }
        return new CollisionInfo(colPoint, colObject);
    }
}