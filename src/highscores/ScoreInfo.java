package highscores;

import java.io.Serializable;

/**
 * Class for score information , implements serializable so it can be saved.
 */
public class ScoreInfo implements Serializable {
    //Members.
    private String name;
    private int score;

    /**
     * Constructor.
     *
     * @param name  player's name.
     * @param score player's score.
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * getName.
     *
     * @return player's name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * getScore.
     *
     * @return player's score.
     */
    public int getScore() {
        return this.score;
    }
}