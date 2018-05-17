package files;

import sprites.Block;

import java.awt.Color;
import java.util.Set;
import java.util.TreeMap;

/**
 * BlockFactoryByMap.
 * creates factory by a giving map of symbols and their values.
 */
public class BlockFactoryByMap implements BlockCreator {
    //Members.
    private String symbol;
    private int width;
    private int height;
    private int hitPoints;
    private Color stroke;
    private TreeMap<Integer, String> fillMap;

    /**
     * Constructor.
     *
     * @param symbol symbol.
     * @param map    map value of the symbol.
     * @throws Exception if parseInt is not well.
     */
    public BlockFactoryByMap(String symbol, TreeMap<String, String> map) throws Exception {
        this.symbol = symbol;
        this.checkMap(map);
        if (map.containsKey("width")) {
            this.width = Integer.parseInt(map.get("width"));
        }
        if (map.containsKey("height")) {
            this.height = Integer.parseInt(map.get("height"));
        }
        if (map.containsKey("hit_points")) {
            this.hitPoints = Integer.parseInt(map.get("hit_points"));
        }
        fillMap = new TreeMap<Integer, String>();
        if (this.hitPoints == 1) {
            if (map.containsKey("fill")) {
                String s = map.get("fill");
                fillMap.put(1, s);
            } else if (map.containsKey("fill-1")) {
                String s = map.get("fill-1");
                fillMap.put(1, s);
            }
        }
        if (this.hitPoints > 1) {
            int i = 0;
            if (map.containsKey("fill") && !map.containsKey("fill-1")) {
                String s = map.get("fill");
                fillMap.put(1, s);
                i = 1;
            } else {
                i = 0;
            }
            for (; i < this.hitPoints; i++) {
                String key = "fill-" + Integer.toString(i + 1);
                if (map.containsKey(key)) {
                    String s = map.get(key);
                    fillMap.put((i + 1), s);
                }
            }
        }
        if (map.containsKey("stroke")) {
            String s = map.get("stroke");
            this.stroke = ColorsParser.colorFromString(s);
        } else {
            this.stroke = null;
        }
    }

    /**
     * Create block for this giving type in a different points.
     *
     * @param xpos x position.
     * @param ypos y position.
     * @return Block.
     */
    public Block create(int xpos, int ypos) {
        Block b;
        if (this.stroke == null) {
            b = new Block(xpos, ypos, width, height, fillMap);
        } else {
            b = new Block(xpos, ypos, width, height, fillMap, stroke);
        }
        b.setNumberOnBlock(this.hitPoints);
        return b;
    }

    /**
     * checkMap.
     * checks map has all the needed values.
     *
     * @param map to check.
     * @throws Exception if missing details.
     */
    private void checkMap(TreeMap<String, String> map) throws Exception {
        Set<String> keys = map.keySet();
        String[] necessaryKeys = {"width", "height", "hit_points"};
        for (String s : necessaryKeys) {
            if (!keys.contains(s)) {
                throw new Exception("Missing definitions to block!");
            }
        }
        int numOfHits = Integer.parseInt(map.get("hit_points"));
        int num = numOfHits;
        if (map.containsKey("fill")) {
            numOfHits--;
        }
        int i = 1;
        while (numOfHits > 0) {
            String fill = "fill-" + Integer.toString(i);
            if (map.containsKey(fill)) {
                numOfHits--;
                if (numOfHits == 0) {
                    continue;
                }
            }
            if (i > num + 1) {
                throw new Exception("Missing fill details");
            }
            i++;
        }
    }
}