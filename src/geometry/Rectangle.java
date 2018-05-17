package geometry;
import sprites.Boundaries;

import java.util.ArrayList;

/**
 * Created by Eilon.
 * ID: 308576933.
 * Class for Rectangle.
 */
public class Rectangle {
    //Members
    private double width;
    private double height;
    private Point upperLeft;
    private Boundaries lineBoundaries;

    /**
     * Constructor, create a new rectangle with location and width/height.
     *
     * @param upperLeft upper left point.
     * @param width     width.
     * @param height    height.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        Point lowerRight = new Point(upperLeft.getX() + width, getUpperLeft().getY() + height);
        this.lineBoundaries = new Boundaries(upperLeft, lowerRight);
    }

    /**
     * Constructor, create a new rectangle with location and width/height.
     *
     * @param x      x of upper left point.
     * @param y      y of upper left point.
     * @param width  width.
     * @param height height.
     */
    public Rectangle(double x, double y, double width, double height) {
        this.upperLeft = new Point(x, y);
        this.width = width;
        this.height = height;
        Point lowerRight = new Point(upperLeft.getX() + width, getUpperLeft().getY() + height);
        this.lineBoundaries = new Boundaries(upperLeft, lowerRight);
    }

    /**
     * intersectionPoints.
     * Return a (possibly empty) List of intersection points
     * with the specified line.
     *
     * @param line to check if intersect with this rectangle.
     * @return list of intersection points.
     */
    public ArrayList<Point> intersectionPoints(Line line) {
        ArrayList<Point> intersectionPointsArray = new ArrayList<Point>();
        Point upperLinePoint = this.lineBoundaries.getUpperLine().intersectionWith(line);
        Point lowerLinePoint = this.lineBoundaries.getLowerLine().intersectionWith(line);
        Point rightLinePoint = this.lineBoundaries.getRightLine().intersectionWith(line);
        Point leftLinePoint = this.lineBoundaries.getLeftLine().intersectionWith(line);
        if (upperLinePoint != null) {
            intersectionPointsArray.add(upperLinePoint);
        }
        if (lowerLinePoint != null) {
            intersectionPointsArray.add(lowerLinePoint);
        }
        if (rightLinePoint != null) {
            intersectionPointsArray.add(rightLinePoint);
        }
        if (leftLinePoint != null) {
            intersectionPointsArray.add(leftLinePoint);
        }
        return intersectionPointsArray;
    }

    /**
     * Return the width of the rectangle.
     *
     * @return rectangle's width.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Return the height of the rectangle.
     *
     * @return rectangle's height.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return upper left point of rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * getBoundaries.
     *
     * @return rectangle's boundaries.
     */
    public Boundaries getBoundaries() {
        return this.lineBoundaries;
    }
}