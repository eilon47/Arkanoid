package animations;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * AnimationRunner class.
 * The class gets an animation instances and runs it.
 * Created by Eilon.
 */
public class AnimationRunner {
    //Members.
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;
    private double dt;

    /**
     * Constructor.
     *
     * @param gui             GUI.
     * @param framesPerSecond how many frames should run per seoond.
     * @param sleeper         sleeper.
     */
    public AnimationRunner(GUI gui, int framesPerSecond, Sleeper sleeper) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
        this.sleeper = sleeper;
        this.dt = (1.0 / this.framesPerSecond);
    }

    /**
     * run.
     * the method gets an animation and runs it, by calling the animation's
     * method doOneFrame.
     *
     * @param animation animation instance.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d, this.dt);
            this.gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}