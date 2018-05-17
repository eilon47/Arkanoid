package highscores;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class for highscore table.
 */
public class HighScoresTable {
    //Members.
    private int size;
    private List<ScoreInfo> scores;
    //Default size if the user did not eneter one.
    private static final int DEFAULT_SIZE = 5;

    /**
     * Constructor.
     *
     * @param size size of the table.
     */
    public HighScoresTable(int size) {
        this.size = size;
        this.scores = new ArrayList<ScoreInfo>();
    }

    /**
     * Constructor.
     * For default high scores table.
     */
    public HighScoresTable() {
        this.size = DEFAULT_SIZE;
        this.scores = new ArrayList<ScoreInfo>();
    }

    /**
     * add.
     * Adds high score to the table.
     *
     * @param score score info.
     */
    public void add(ScoreInfo score) {
        this.scores.add(score);
        //sort list.
        this.sortList();
        if (this.scores.size() > this.size()) {
            this.scores.remove(this.size);
        }
    }

    /**
     * size.
     *
     * @return size of the table.
     */
    public int size() {
        return this.size;
    }

    /**
     * getHighScores.
     *
     * @return list of scores.
     */
    public List<ScoreInfo> getHighScores() {
        return this.scores;
    }

    /**
     * getRank.
     * Checks which rank the score paramter will be if it enters the table.
     *
     * @param score score to check.
     * @return the possible rank of the score in the table.
     */
    public int getRank(int score) {
        List<Integer> list = new ArrayList<Integer>();
        List<Integer> listReverse = new ArrayList<Integer>();
        if (this.scores.size() == this.size()) {
            if (this.scores.get(this.size() - 1).getScore() >= score) {
                return this.size + 1;
            }
        }
        for (ScoreInfo s : this.scores) {
            list.add(s.getScore());
        }
        list.add(score);
        //Sort list.
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            //Reverse it to descending sort.
            listReverse.add(list.get(list.size() - 1 - i));
        }
        return listReverse.indexOf(score) + 1;
    }

    /**
     * clears the table.
     */
    public void clear() {
        this.scores.clear();
    }

    /**
     * Load table data from file.
     * Current table data is cleared.
     *
     * @param filename file name.
     * @throws IOException if it could not open.
     */
    public void load(File filename) throws IOException {
        HighScoresTable hs = HighScoresTable.loadFromFile(filename);
        this.scores.clear();
        this.scores = hs.getHighScores();
    }

    /**
     * Save table data to the specified file.
     *
     * @param filename file name.
     * @throws IOException if it could not save.
     */
    public void save(File filename) throws IOException {
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(filename));
            outputStream.writeObject(this.getHighScores());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    /**
     * Read a table from file and return it.
     * If the file does not exist, or there is a problem with
     * reading it, an empty table is returned.
     *
     * @param filename file name.
     * @return HighScoreTable type.
     */
    public static HighScoresTable loadFromFile(File filename) {
        ObjectInputStream inputStream = null;
        HighScoresTable hs = new HighScoresTable();
        try {
            inputStream = new ObjectInputStream(new FileInputStream(filename));
            List<ScoreInfo> scoreInfoList = (List<ScoreInfo>) inputStream.readObject();
            if (scoreInfoList != null) {
                hs.clear();
                hs.getHighScores().addAll(scoreInfoList);
            }
        } catch (IOException e1) {
            System.out.println(e1.toString());
        } catch (ClassNotFoundException e2) {
            System.out.println(e2.toString());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e3) {
                    System.out.println(e3.toString());
                }
            }
        }
        return hs;
    }

    /**
     * sortList.
     * Sorts scoreInfo list by the scores of each score info.
     */
    public void sortList() {
        for (int i = 0; i < this.scores.size() - 1; i++) {
            for (int j = 0; j < (this.scores.size() - i - 1); j++) {
                if (this.scores.get(j).getScore() < this.scores.get(j + 1).getScore()) {
                    ScoreInfo tempScore = this.scores.get(j);
                    this.scores.set(j, this.scores.get(j + 1));
                    this.scores.set((j + 1), tempScore);
                }
            }
        }
    }
}