package menu;
import animations.AnimationRunner;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Class for sub menu, extends MenuAnimatio and implements Task.
 * @param <T> type of task.
 */
public class SubMenu<T> extends MenuAnimation implements Task<Void> {
    private AnimationRunner ar;
    private KeyboardSensor ks;

    /**
     * Constructor.
     *
     * @param ks keyboard sensor.
     * @param ar animation runner.
     */
    public SubMenu(KeyboardSensor ks, AnimationRunner ar) {
        super(ks);
        this.ar = ar;
        this.ks = ks;
    }

    /**
     * runs the task.
     *
     * @return null.
     */
    public Void run() {
        while (true) {
            this.ar.run(this);
            Task<Void> t = this.getStatus();
            if (t.run() == null) {
                break;
            }
            if (t != null) {
                t.run();
            }
        }
        this.cleanTasks();
        return null;
    }

    @Override
    public String menuString() {
        return "Select level:";
    }

    @Override
    public void drawLogo(DrawSurface d) {
    }
}