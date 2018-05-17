package geometry;

/**
 * Created by Eilon.
 * ID: 308576933.
 * Class for point, each point has a variables x and y.
 * Methods to calculate the distance between 2 points and for comparing 2 points.
 */
public class Point {
    //Members.
    private double x;
    private double y;

    /**
     * Constructor for point.
     *
     * @param x x variable from the user to the new point.
     * @param y y variable from the user to the new point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * distance.
     * A method to calculate the distance between 2 points.
     *
     * @param other , another point to calculate the distance to it.
     * @return return the distance of this point to the other point.
     */
    public double distance(Point other) {
        double powXs = (this.x - other.x) * (this.x - other.x);
        double powYs = (this.y - other.y) * (this.y - other.y);
        return Math.sqrt(powXs + powYs);
    }

    /**
     * equals.
     * Checks if this point equals to another.
     *
     * @param other , another point to compare to this point.
     * @return return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        if (this.x == other.x) {
            if (this.y == other.y) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the x value of this point.
     *
     * @return x value.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns the y value of this point.
     *
     * @return y value.
     */
    public double getY() {
        return this.y;
    }
}