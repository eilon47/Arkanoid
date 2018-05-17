package files;
import java.awt.Color;
/**
 * ColorParser class, creates color from string.
 */
public class ColorsParser {

    /**
     * parse color definition and return the specified color.
     * @param s string with name of color or rgb value.
     * @return Color.
     */
    public static java.awt.Color colorFromString(String s) {
        s = s.toLowerCase();
       if (s.contains("rgb")) {
           s = s.replace("color(rgb(", "");
           s = s.replace("))", "");
           String[] colors = s.split(",");
           int r = Integer.parseInt(colors[0]);
           int g = Integer.parseInt(colors[1]);
           int b = Integer.parseInt(colors[2]);
           return new Color(r, g, b);
       } else {
           s = s.replace("color(", "");
           s = s.replace(")", "");
           if (s.equals("cyan")) {
               return Color.CYAN;
           } else if (s.equals("blue")) {
               return Color.BLUE;
           } else if (s.equals("black")) {
               return Color.BLACK;
           } else if (s.equals("red")) {
               return Color.RED;
           } else if (s.equals("pink")) {
               return Color.PINK;
           } else if (s.equals("yellow")) {
               return Color.YELLOW;
           } else if (s.equals("lightgray")) {
               return Color.LIGHT_GRAY;
           } else if (s.equals("gray")) {
               return Color.GRAY;
           } else if (s.equals("darkgray")) {
               return Color.DARK_GRAY;
           } else if (s.equals("orange")) {
               return Color.ORANGE;
           } else if (s.equals("white")) {
               return Color.white;
           } else if (s.equals("magenta")) {
               return Color.magenta;
           } else if (s.equals("green")) {
            return Color.green;
        }
           //Default
           return Color.black;
       }
    }
}