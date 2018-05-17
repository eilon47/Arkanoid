package sprites;
import geometry.Line;
import geometry.Point;
/**
 * Created by Eilon.
 * ID: 308576933.
 * Class Boundaries.
 * Each ball has boundaries where it can bounce.
 */
public class Boundaries {
    //Members.
    private Line horizontalHigh;
    private Line horizontalLow;
    private Line verticalRight;
    private Line verticalLeft;

    /**
     * Constructor.
     * Makes 4 lines as borders from 2 given points.
     *
     * @param p1 first given point, top left corner.
     * @param p2 second given point, low right corner.
     */
    public Boundaries(Point p1, Point p2) {
        horizontalHigh = new Line(p1.getX(), p1.getY(), p2.getX(), p1.getY());
        horizontalLow = new Line(p1.getX(), p2.getY(), p2.getX(), p2.getY());
        verticalLeft = new Line(p1.getX(), p1.getY(), p1.getX(), p2.getY());
        verticalRight = new Line(p2.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    /**
     * Returns the y of the top horizontal border.
     *
     * @return y of top horizontal border
     */
    public double getHighY() {
        return horizontalHigh.start().getY();
    }

    /**
     * Returns the y of the low horizontal border.
     *
     * @return y of low horizontal border
     */
    public double getLowY() {
        return horizontalLow.start().getY();
    }

    /**
     * Returns the x of the right vertical border.
     *
     * @return x of right  border
     */
    public double getRightX() {
        return verticalRight.start().getX();
    }

    /**
     * Returns the x of the left vertical border.
     *
     * @return x of left  border
     */
    public double getLeftX() {
        return verticalLeft.start().getX();
    }

    /**
     * getUpperLine.
     *
     * @return horizontal high border.
     */
    public Line getUpperLine() {
        return horizontalHigh;
    }

    /**
     * getLowerLine.
     *
     * @return horizontal low border.
     */
    public Line getLowerLine() {
        return horizontalLow;
    }

    /**
     * getRightLine.
     *
     * @return vertical right border.
     */
    public Line getRightLine() {
        return verticalRight;
    }

    /**
     * getLeftLine.
     *
     * @return vertical left border.
     */
    public Line getLeftLine() {
        return verticalLeft;
    }
}