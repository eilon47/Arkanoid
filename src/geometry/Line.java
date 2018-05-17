package geometry;
import java.util.ArrayList;

/**
 * Created by Eilon.
 * ID: 308576933.
 * Class for Line, each line segment has 2 points - start and end.
 * Methods to calculate the length of this line, this line middle point, this line gradient,
 * methods to check if this line segment is intersecting with another line segment and the intersection point
 * of them, and if a point lies on this line.
 */
public class Line {
    //Members.
    private Point start;
    private Point end;

    /**
     * Constructor for line made by given 2 points.
     *
     * @param start the point the line segment start from.
     * @param end   the point the line segment ends.
     */
    public Line(Point start, Point end) {
        //Set the lower x as start point.
        if (start.getX() < end.getX()) {
            this.start = new Point(start.getX(), start.getY());
            this.end = new Point(end.getX(), end.getY());
        } else {
            this.end = new Point(start.getX(), start.getY());
            this.start = new Point(end.getX(), end.getY());
        }
    }

    /**
     * Constructor to line made of 4 double variables.
     *
     * @param x1 variable from the user.
     * @param y1 variable from the user.
     * @param x2 variable from the user.
     * @param y2 variable from the user.
     */
    public Line(double x1, double y1, double x2, double y2) {
        if (x1 < x2) {
            this.start = new Point(x1, y1);
            this.end = new Point(x2, y2);
        } else {
            this.end = new Point(x1, y1);
            this.start = new Point(x2, y2);
        }
    }

    /**
     * length.
     * Method to calculate this line length.
     *
     * @return Return the length of the line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * middle.
     * Method to calculate the middle point of this line.
     *
     * @return Returns the middle point of the line
     */
    public Point middle() {
        double midX = ((start.getX() + end.getX()) / 2);
        double midY = ((start.getY() + end.getY()) / 2);
        Point mid = new Point(midX, midY);
        return mid;
    }

    /**
     * start.
     * Methods that gives back the start point of a line segment.
     *
     * @return Returns the start point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * end.
     * Methods that gives back the end point of a line segment.
     *
     * @return Returns the end point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * gradient.
     * Method to calculate gradient of this line segment.
     *
     * @return the gradient of this line.
     */
    public double gradient() {
        //The formula to calculate the gradient.
        double gradient;
        double deltaY = this.start.getY() - this.end.getY();
        double deltaX = this.start.getX() - this.end.getX();
        //The line is vertical.
        if (deltaX == 0 && deltaY != 0) {
            gradient = Double.POSITIVE_INFINITY;
            return gradient;
        }
        //The line is horizontal.
        if (deltaY == 0 && deltaX != 0) {
            gradient = 0;
            return gradient;
        }
        gradient = deltaY / deltaX;
        return gradient;
    }

    /**
     * intersectionWith.
     * checks if this line cross other.
     *
     * @param other another line.
     * @return Returns the intersection point if the lines intersect, null otherwise.
     */
    public Point intersectionWith(Line other) {
        double infinty = Double.POSITIVE_INFINITY;
        double m1 = this.gradient();
        double m2 = other.gradient();
        double x, y;
        Point nPoint;
        //if the line segments has the same gradient.
        if (this.gradient() == other.gradient()) {
            return null;
        }
        /*
        if one of the lines is vertical
        checks x from each side and find the intersection point.
        */
        if (this.gradient() == infinty) {
            x = this.start.getX();
            if (other.start.getX() < x
                    && other.end.getX() > x) {
                y = (m2 * (x - other.start.getX()) + other.start.getY());
                nPoint = new Point(x, y);
                if (this.pointInLineIntersection(nPoint) && other.pointInLineIntersection(nPoint)) {
                    return nPoint;
                }
                return null;
            }
            return null;
        }
        if (other.gradient() == infinty) {
            x = other.start.getX();
            if (this.start.getX() < x
                    && this.end.getX() > x) {
                y = (m1 * (x - this.start.getX()) + this.start.getY());
                nPoint = new Point(x, y);
                if (this.pointInLineIntersection(nPoint) && other.pointInLineIntersection(nPoint)) {
                    return nPoint;
                }
                return null;
            }
            return null;
        }
        /*
        if one of the lines is horizontal
        checks y from above and under and finds the intersection point.
        */
        if (this.gradient() == 0) {
            y = this.start.getY();
            if ((other.start.getY() > y && other.end.getY() < y)
                    || other.start.getY() < y && other.end.getY() > y) {
                x = ((y - other.start.getY()) / other.gradient()) + other.start.getX();
                nPoint = new Point(x, y);
                if (this.pointInLineIntersection(nPoint) && other.pointInLineIntersection(nPoint)) {
                    return nPoint;
                }
                return null;
            }
            return null;
        }
        if (other.gradient() == 0) {
            y = other.start.getY();
            if ((this.start.getY() > y && this.end.getY() < y)
                    || this.start.getY() < y && this.end.getY() > y) {
                x = ((y - this.start.getY()) / this.gradient()) + this.start.getX();
                nPoint = new Point(x, y);
                if (this.pointInLineIntersection(nPoint) && other.pointInLineIntersection(nPoint)) {
                    return nPoint;
                }
                return null;
            }
            return null;
        }
        //if the gradients are different from each other and not equal to zero or infinity.
        x = ((this.gradient() * this.start.getX()) - this.start.getY()
                - (other.gradient() * other.start.getX()) + other.start.getY())
                / (this.gradient() - other.gradient());
        y = (this.gradient() * (x - this.start.getX())) + this.start.getY();
        nPoint = new Point(x, y);
        if (this.pointInLineIntersection(nPoint) && other.pointInLineIntersection(nPoint)) {
            return nPoint;
        }
        return null;
    }

    /**
     * closestIntersectionToStartOfLine.
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     *
     * @param rect rectangle to check the intersection with.
     * @return closest point.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        ArrayList<Point> intersectionPoints = rect.intersectionPoints(this);
        if (intersectionPoints.isEmpty()) {
            return null;
        } else if (intersectionPoints.size() == 1) {
            return intersectionPoints.get(0);
        } else {
            double dis0 = this.start.distance(intersectionPoints.get(0));
            double dis1 = this.start.distance(intersectionPoints.get(1));
            if (dis0 > dis1) {
                return intersectionPoints.get(1);
            }
            return intersectionPoints.get(0);
        }
    }

    /**
     * pointLiesOnLine.
     * Using the line equation to check if a point can lie on this line.
     * Using the line equation to check if a point can lie on this line.
     *
     * @param point a point the user wants to check.
     * @return the method return true if there is a possibility that the point lies on the line
     * and false otherwise.
     */
    public boolean pointLiesOnLine(Point point) {
        if (point == null) {
            return false;
        }
        double y = point.getY();
        double x = point.getX();
        double x1 = this.start.getX();
        double x2 = this.end.getX();
        double y1 = this.start.getY();
        double y2 = this.end.getY();
        double m = this.gradient();
        if (m == Double.POSITIVE_INFINITY) {
            return ((x1 <= x && x <= x2) || (x1 >= x && x >= x2));
        }
        if (m == 0) {
            return ((y1 <= y && y <= y2) || (y1 >= y && y >= y2));
        }
        return (y == (m * (x - this.start().getX()) + this.start().getY()));
    }

    /**
     * PointInLineIntersection.
     * The method get a point which may be the line intersection point and
     * return true if it is.
     *
     * @param point a point which is suspicious as the intersection point.
     * @return true if the point is in both lines, false otherwise.
     */
    public boolean pointInLineIntersection(Point point) {
        return (Math.max(this.end.distance(point), this.start.distance(point))
                <= this.start.distance(this.end));
    }

    /**
     * isIntersecting.
     * Checks if this lines is intersecting with the another line.
     *
     * @param other another line segment.
     * @return true if the lines are intersecting and false otherwise.
     */
    public boolean isIntersecting(Line other) {
        if (this.intersectionWith(other) != null) {
            return true;
        }
        return false;
    }

    /**
     * equals.
     * Method to compare if this line is the same as the other.
     *
     * @param other another line.
     * @return return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        //If the lines starts at the same point and ends in the same points.
        if (this.start.equals(other.start)
                && this.end.equals(other.end)) {
            return true;
        }
        //If the lines are opposites.
        if (this.start().equals(other.end)
                && this.end().equals(other.start)) {
            return true;
        }
        return false;
    }
}