package files;

import biuoop.DrawSurface;
import levels.LevelInformation;
import sprites.Block;
import sprites.Sprite;
import sprites.Velocity;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.io.InputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

/**
 * Creates Level Information.
 */
public class MakeLevelInfo {
    /**
     * Method to create level information type from giving information in maps.
     *
     * @param level  level's map.
     * @param blocks blocks's map.
     * @return LevelInformation type.
     */
    public LevelInformation makeLevelInfo(TreeMap<String, String> level, TreeMap<String, String> blocks) {
        //Get name.
        String levelName = level.get("level_name");
        //Get velocities.
        List<Velocity> velocities = new ArrayList<Velocity>();
        String[] velocity = level.get("ball_velocities").split(" ");
        for (String v : velocity) {
            String[] angleAndSpeed = v.split(",");
            int angle = Integer.parseInt(angleAndSpeed[0]);
            int speed = Integer.parseInt(angleAndSpeed[1]);
            velocities.add(Velocity.fromAngleAndSpeed(angle, speed));
        }
        final int pSpeed = Integer.parseInt(level.get("paddle_speed"));
        final int pWidth = Integer.parseInt(level.get("paddle_width"));
        final int numOfBlocks = Integer.parseInt(level.get("num_blocks"));
        final int startX = Integer.parseInt(level.get("blocks_start_x"));
        final int startY = Integer.parseInt(level.get("blocks_start_y"));
        final int rowHeight = Integer.parseInt(level.get("row_height"));
        //Get background.
        String backgroundString = level.get("background");
        final Sprite background = this.getBackground(backgroundString);
        final int numOfBalls = velocities.size();
        //Creates blocks.
        List<Block> blockList = null;
        String pathToBlockDef = level.get("block_definitions");
        BlocksFromSymbolsFactory blockFactory = null;
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(pathToBlockDef);
        Reader reader = new InputStreamReader(is);
        blockFactory = BlockDefinitionReader.fromReader(reader);
        blockList = this.fromMapToList(blocks, startX, startY, blockFactory, rowHeight);
        final List<Block> list = blockList;
        //Returns type of new LevelInformation.
        return new LevelInformation() {
            @Override
            public int numberOfBalls() {
                return numOfBalls;
            }

            @Override
            public List<Velocity> initialBallVelocities() {
                return velocities;
            }

            @Override
            public int paddleSpeed() {
                return pSpeed;
            }

            @Override
            public int paddleWidth() {
                return pWidth;
            }

            @Override
            public String levelName() {
                return levelName;
            }

            @Override
            public Sprite getBackground() {
                return background;
            }

            @Override
            public List<Block> blocks() {
                return list;
            }

            @Override
            public int numberOfBlocksToRemove() {
                return numOfBlocks;
            }
        };
    }

    /**
     * fromMapToList.
     * creates block's list from map that holds information of each blocks.
     *
     * @param blocksMap how the blocks aranged on screen.
     * @param xStart    x start for upper left block.
     * @param yStart    y start for upper left block.
     * @param factory   block factory.
     * @param rowHeight row height between rows.
     * @return list of blocks.
     */
    public List<Block> fromMapToList(TreeMap<String, String> blocksMap, int xStart,
                                     int yStart, BlocksFromSymbolsFactory factory, int rowHeight) {
        List<Block> blockList = new ArrayList<Block>();
        Set<String> rowSet = blocksMap.keySet();
        for (String row : rowSet) {
            String[] symbolsString = blocksMap.get(row).split("");
            int xInThisRow = xStart;
            for (String symbol : symbolsString) {
                if (factory.isSpaceSymbol(symbol)) {
                    xInThisRow = xInThisRow + factory.getSpaceWidth(symbol);
                }
                if (factory.isBlockSymbol(symbol)) {
                    Block b = factory.getBlock(symbol, xInThisRow, yStart);
                    xInThisRow = xInThisRow + (int) b.getCollisionRectangle().getWidth();
                    blockList.add(b);
                }
            }
            yStart = yStart + rowHeight;
        }
        return blockList;
    }

    /**
     * getBackground.
     * reads the string of the background and returns sprite as a background with the information in it.
     *
     * @param backgroundString string of background - color or image.
     * @return sprite type for background.
     */
    public Sprite getBackground(String backgroundString) {
        int backgroundFlag = 0;
        Image img = null;
        Color backgroundColor = null;
        if (backgroundString.contains("color")) {
            backgroundColor = ColorsParser.colorFromString(backgroundString);
            backgroundFlag = 1;
        } else {
            backgroundString = backgroundString.replace("image(", "");
            String pathToImg = backgroundString.replace(")", "");
            // load the image data into an java.awt.Image object
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(pathToImg);
            try {
                img = ImageIO.read(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        final Image bgImg = img;
        final int flag = backgroundFlag;
        final Color bgColor = backgroundColor;
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                if (flag == 0) {
                    d.drawImage(0, 0, bgImg);
                } else {
                    d.setColor(bgColor);
                    d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
                }
            }

            @Override
            public void timePassed(double dt) {

            }
        };
    }
}