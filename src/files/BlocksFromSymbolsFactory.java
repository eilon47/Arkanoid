package files;

import sprites.Block;

import java.util.Map;

/**
 * BlocksFromSymbolsFactory.
 * Factory for blocks.
 */
public class BlocksFromSymbolsFactory {
    //Members.
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * Constructor.
     *
     * @param spacer        map of space symbols.
     * @param blockCreators map for blocks.
     */
    public BlocksFromSymbolsFactory(Map<String, Integer> spacer, Map<String, BlockCreator> blockCreators) {
        this.spacerWidths = spacer;
        this.blockCreators = blockCreators;
    }

    /**
     * isSpaceSymbol.
     *
     * @param s string.
     * @return returns true if 's' is a valid space symbol.
     */
    public boolean isSpaceSymbol(String s) {
        return this.spacerWidths.containsKey(s);
    }

    /**
     * getBlockSymbol.
     *
     * @param s string.
     * @return returns true if 's' is a valid block symbol.
     */
    public boolean isBlockSymbol(String s) {
        return this.blockCreators.containsKey(s);
    }

    /**
     * Return a block according to the definitions associated
     * with symbol s. The block will be located at position (xpos, ypos).
     *
     * @param s symbol.
     * @param x x position.
     * @param y y position.
     * @return new Block.
     */
    public Block getBlock(String s, int x, int y) {
        return this.blockCreators.get(s).create(x, y);
    }

    /**
     * Returns the width in pixels associated with the given spacer-symbol.
     *
     * @param s symbol.
     * @return symbol's space.
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }
}