package files;

import levels.LevelInformation;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 * LevelSpecificationReader.
 * the class gets a file to read and returns list of level information.
 */
public class LevelSpecificationReader {
    /**
     * The method gets reader and returns list of level informations.
     *
     * @param reader reader of file.
     * @return list of level information.
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        List<LevelInformation> levelInformationList = new ArrayList<LevelInformation>();
        String[] levelMapKeys = {"level_name", "ball_velocities", "background", "paddle_speed",
                "paddle_width", "block_definitions", "blocks_start_x", "blocks_start_y",
                "row_height", "num_blocks"};
        MakeLevelInfo infoMaker = new MakeLevelInfo();
        BufferedReader br = new BufferedReader(reader);
        try {
            String line = br.readLine().trim();
            while (line != null) {
                if (line.contains("#") || line.equals("")) {
                    line = br.readLine().trim();
                    continue;
                }
                if (line.contains("START_LEVEL")) {
                    List<TreeMap<String, String>> levelInfo = this.getInfoFromFile(br, line);
                    TreeMap<String, String> levelMap = levelInfo.get(0);
                    if (!this.checkMapHasAllKeys(levelMap, levelMapKeys)) {
                        System.out.println("Something wrong with levels files");
                        System.exit(1);
                    }
                    TreeMap<String, String> blocksMap = levelInfo.get(1);
                    //this moment we have 2 maps to make information from them.
                    LevelInformation levelInformation = infoMaker.makeLevelInfo(levelMap, blocksMap);
                    levelInformationList.add(levelInformation);
                    blocksMap.clear();
                    levelMap.clear();
                }
                line = br.readLine();
            }
            return levelInformationList;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * the method gets reader of file and returns two maps. one of the level and other of the blocks.
     *
     * @param bufferedReader reader.
     * @param currentLine    current line on buffered reader.
     * @return list of 2 maps.
     */
    public List<TreeMap<String, String>> getInfoFromFile(BufferedReader bufferedReader, String currentLine) {
        TreeMap<String, String> levelMap = new TreeMap<String, String>();
        TreeMap<String, String> blocksMap = new TreeMap<String, String>();
        List<TreeMap<String, String>> list = new ArrayList<TreeMap<String, String>>();
        String line = currentLine;
        try {
            if (line.contains("START_LEVEL")) {
                while (!line.contains("END_LEVEL")) {
                    if (line.contains("START_BLOCKS")) {
                        line = bufferedReader.readLine().trim();
                        int i = 0;
                        while (!line.contains("END_BLOCKS")) {
                            String rowNum = null;
                            if (i > 9) {
                                rowNum = "row9" + Integer.toString(i);
                            } else {
                                rowNum = "row" + Integer.toString(i);
                            }
                            blocksMap.put(rowNum, line);
                            line = bufferedReader.readLine().trim();
                            i++;
                        }
                    }
                    if (line.contains(":")) {
                        String[] keyValue = line.split(":");
                        levelMap.put(keyValue[0], keyValue[1]);
                    }
                    line = bufferedReader.readLine().trim();

                }
            }
        } catch (IOException e) {
            System.out.println("Could not read file.");
        }
        list.add(levelMap);
        list.add(blocksMap);
        return list;
    }

    /**
     * checkMapHasAllKeys.
     * checks the map has all the keys in a giving string array.
     *
     * @param map  map to check.
     * @param keys keys to check the map has them.
     * @return true or false.
     */
    public boolean checkMapHasAllKeys(Map<String, String> map, String[] keys) {
        for (String s : keys) {
            if (!map.containsKey(s)) {
                return false;
            }
        }
        return true;
    }
}