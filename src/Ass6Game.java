import animations.Animation;
import animations.AnimationRunner;
import animations.GameFlow;
import animations.KeyPressStoppableAnimation;
import biuoop.GUI;
import biuoop.DialogManager;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import files.CreatesLevelsFromSet;
import highscores.HighScoresAnimation;
import highscores.HighScoresTable;
import java.io.InputStream;
import java.io.InputStreamReader;
import levels.LevelInformation;
import menu.Menu;
import menu.MenuAnimation;
import menu.SubMenu;
import menu.Task;
import java.io.File;
import java.io.Reader;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;


/**
 * Ass6Game. class for main.
 */
public class Ass6Game {
    /**
     * main.
     *
     * @param args path to level sets.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        DialogManager dialog = gui.getDialogManager();
        AnimationRunner ar = new AnimationRunner(gui, framesPerSecond, sleeper);
        KeyboardSensor ks = gui.getKeyboardSensor();
        Menu<Task<Void>> menu = new MenuAnimation(ks);
        String path = null;
        //Open level set.
        if (args.length > 0) {
            path = args[0];
        } else {
            path = "level_sets.txt";

        }
        while (true) {
            addTasksToMenu(menu, path, ar, ks, dialog);
            ar.run(menu);
            Task<Void> t = menu.getStatus();
            if (t != null) {
                t.run();
            }
            menu.cleanTasks();
        }
    }

    /**
     * addTasksToMenu.
     * the method adds tasks to the menu.
     *
     * @param menu   main menu.
     * @param levels string of the path to the levels specification.
     * @param ar     animation runner.
     * @param ks     keyboard sensor.
     * @param dialog dialog manager.
     */
    public static void addTasksToMenu(Menu<Task<Void>> menu, String levels, AnimationRunner ar,
                                      KeyboardSensor ks, DialogManager dialog) {
        InputStream is = null;
        is = ClassLoader.getSystemClassLoader().getResourceAsStream(levels);
        CreatesLevelsFromSet clfs = new CreatesLevelsFromSet();
        Reader reader = new InputStreamReader(is);
        TreeMap<String, List<LevelInformation>> difLevelMap = clfs.getMapByDif(reader);

        //High score table.
        HighScoresTable highScoresTable = null;
        File highscoreFile = new File("highscores");
        if (highscoreFile.exists()) {
            highScoresTable = HighScoresTable.loadFromFile(highscoreFile);
        } else {
            highScoresTable = new HighScoresTable(5);
        }
        Animation highscore = new KeyPressStoppableAnimation(ks,
                KeyboardSensor.SPACE_KEY, new HighScoresAnimation(highScoresTable));
        HighScoresTable hst = highScoresTable;
        SubMenu<Void> subMenu = new SubMenu<Void>(ks, ar);
        Set<String> mapKeys = difLevelMap.keySet();
        for (String s : mapKeys) {
            String[] split = s.split(":");
            String key = split[0].substring(1);
            subMenu.addSelection(key, split[1], new Task<Void>() {
                @Override
                public Void run() {
                    GameFlow game = new GameFlow(ar, ks, dialog, hst, highscoreFile);
                    game.runLevels(difLevelMap.get(s));
                    return null;
                }
            });
        }
        Task<Void> highscoreTask = new Task<Void>() {
            @Override
            public Void run() {
                ar.run(highscore);
                return null;
            }
        };
        Task<Void> exitTask = new Task<Void>() {
            @Override
            public Void run() {
                System.exit(1);
                return null;
            }
        };
        Task<Void> backToMainMenu = new Task<Void>() {
            @Override
            public Void run() {
                return null;
            }
        };
        subMenu.addSelection("b", "Back", backToMainMenu);
        menu.addSelection("s", "Start Game", subMenu);
        menu.addSelection("h", "Highscores", highscoreTask);
        menu.addSelection("q", "Quit", exitTask);
    }
}