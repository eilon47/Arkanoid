package files;

import levels.LevelInformation;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.IOException;
import java.util.List;
import java.util.TreeMap;
import java.util.ArrayList;

/**
 * Creates levels from set information.
 */
public class CreatesLevelsFromSet {
    /**
     * getMapByDif.
     * creates a map that the key is the type of levels and the value is list of level information.
     *
     * @param reader reader.
     * @return map of difficulties and levels.
     */
    public TreeMap<String, List<LevelInformation>> getMapByDif(java.io.Reader reader) {
        BufferedReader bufferedReader = null;
        TreeMap<String, List<LevelInformation>> levelsDifMap = new TreeMap<>();
        try {
            bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
            int i = 0;
            while (line != null) {
                String key;
                List<LevelInformation> value = new ArrayList<>();
                line = line.trim();
                key = Integer.toString(i) + line;
                line = bufferedReader.readLine().trim();
                InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(line);
                ;
                LevelSpecificationReader lsr = new LevelSpecificationReader();
                InputStreamReader isr = new InputStreamReader(is);
                value = lsr.fromReader(isr);
                levelsDifMap.put(key, value);
                line = bufferedReader.readLine();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return levelsDifMap;
    }
}