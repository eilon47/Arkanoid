package files;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Set;
import java.util.TreeMap;

/**
 * BlockDefinitionReader class. reads the definitions of the blocks and return factory.
 */
public class BlockDefinitionReader {

    /**
     * fromReader.
     * method reads blocks definitions and returns new factory.
     *
     * @param reader of the definitions files.
     * @return factory.
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        TreeMap<String, TreeMap<String, String>> blockSymbol = new TreeMap<String, TreeMap<String, String>>();
        TreeMap<String, Integer> spaceSymbols = new TreeMap<String, Integer>();
        TreeMap<String, String> defaultsMap = new TreeMap<String, String>();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
            while (line != null) {
                line = line.trim();
                if (line.contains("#")) {
                    line = bufferedReader.readLine();
                    continue;
                }
                if (line.contains("default")) {
                    String[] lineSplit = line.split(" ");
                    for (String s : lineSplit) {
                        if (s.contains(":")) {
                            String[] split = s.split(":");
                            defaultsMap.put(split[0], split[1]);
                        }
                    }
                }
                if (line.contains("bdef")) {
                    TreeMap<String, String> inValueMap = new TreeMap<String, String>();
                    line = line.replace("bdef symbol:", "");
                    String[] split = line.split(" ");
                    String key = split[0];
                    for (String s : split) {
                        if (s.contains(":")) {
                            String[] mapSplit = s.split(":");
                            inValueMap.put(mapSplit[0], mapSplit[1]);
                        }
                    }
                    blockSymbol.put(key, inValueMap);
                }
                if (line.contains("sdef")) {
                    line = line.replace("sdef symbol:", "");
                    String[] split = line.split(" ");
                    String key = split[0];
                    Integer value = Integer.parseInt(split[1].replace("width:", ""));
                    spaceSymbols.put(key, value);
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Set<String> blockKeys = blockSymbol.keySet();
        for (String key : blockKeys) {
            TreeMap<String, String> map = blockSymbol.get(key);
            TreeMap<String, String> newMap = mergeInValueAndDefaults(map, defaultsMap);
            blockSymbol.replace(key, map, newMap);
        }
        try {
            TreeMap<String, BlockCreator> blockCreatorMap = fromKeyMapToKeyCreator(blockSymbol);
            return new BlocksFromSymbolsFactory(spaceSymbols, blockCreatorMap);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        return null;
    }

    /**
     * mergeInValueAndDefaults.
     * method that merge two maps, checks if key is in default and not in inValue map,
     * if it doesn't it adds it to the inValue map.
     *
     * @param inValue  map.
     * @param defaults map.
     * @return inValue map after merge.
     */
    private static TreeMap<String, String> mergeInValueAndDefaults(TreeMap<String, String> inValue,
                                                                   TreeMap<String, String> defaults) {
        Set<String> inDefaultsKeys = defaults.keySet();
        for (String key : inDefaultsKeys) {
            if (!inValue.containsKey(key)) {
                inValue.put(key, defaults.get(key));
            }
        }
        return inValue;
    }

    /**
     * fromKeyMapToKeyCreator.
     * method that changes the map to key-creator map.
     *
     * @param map map to change.
     * @return new map.
     * @throws Exception e.
     */
    private static TreeMap<String, BlockCreator> fromKeyMapToKeyCreator(TreeMap<String, TreeMap<String, String>> map)
            throws Exception {
        Set<String> keys = map.keySet();
        TreeMap<String, BlockCreator> mapToRet = new TreeMap<String, BlockCreator>();
        for (String key : keys) {
            TreeMap<String, String> valueMap = map.get(key);
            BlockCreator bc = new BlockFactoryByMap(key, valueMap);
            mapToRet.put(key, bc);
        }

        return mapToRet;
    }
}