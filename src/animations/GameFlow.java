package animations;

import biuoop.DialogManager;
import biuoop.KeyboardSensor;
import highscores.HighScoresAnimation;
import highscores.ScoreInfo;
import levels.LevelInformation;
import sprites.Counter;
import sprites.LiveIndicator;
import sprites.ScoreIndicator;

import java.io.File;
import java.io.IOException;
import java.util.List;
import highscores.HighScoresTable;

/**
 * GameFlow.
 * Runs few levels one after the other until the game ends.
 * Created by Eilon.
 */
public class GameFlow {
    //Members.
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private DialogManager dialog;
    private HighScoresTable hst;
    private File filename;
    /**
     * Constructor.
     * @param dialog dialog manager.
     * @param hst highscore table.
     * @param filename name of file.
     * @param ar Animation runner.
     * @param ks Keyboard sensor.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, DialogManager dialog, HighScoresTable hst, File filename) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.hst = hst;
        this.dialog = dialog;
        this.filename = filename;
    }

    /**
     * runLevels.
     * The method gets list of levels and runs them one after the other until
     * the list ends. Then it shows the end screen.
     *
     * @param levels list of levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        //Initialize the score and lives.
        Counter scoreCounter = new Counter(0);
        Counter liveCounter = new Counter(7);
        Boolean winOrLose = false;
        ScoreIndicator scoreIndicator = new ScoreIndicator(scoreCounter);
        LiveIndicator liveIndicator = new LiveIndicator(liveCounter);
        //Runs all the levels.
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, scoreIndicator,
                    liveIndicator);
            level.initialize();
            while (level.getNumOfBalls() > 0 && level.getNumOfBlocks() > 0 && liveCounter.getValue() > 0) {
                level.playOneTurn();
            }
            //Checks if the player won or lose.
            if (liveCounter.getValue() == 0) {
                winOrLose = false;
                break;

            }
            winOrLose = true;
        }
        //Runs the end screen.
        animationRunner.run(new EndScreen(this.keyboardSensor, winOrLose, scoreCounter));
        int possibleRank = this.hst.getRank(scoreCounter.getValue());
        if (possibleRank < this.hst.size()) {
            String name = this.dialog.showQuestionDialog("Name", "What is your name?", "");
            hst.add(new ScoreInfo(name, scoreCounter.getValue()));
            try {
                hst.save(this.filename);
            } catch (IOException e) {
                System.out.println("Could not save file");
            }
        }
        Animation highScoreKeyAnimation = new KeyPressStoppableAnimation(this.keyboardSensor,
                KeyboardSensor.SPACE_KEY, new HighScoresAnimation(this.hst));
        animationRunner.run(highScoreKeyAnimation);

    }
}