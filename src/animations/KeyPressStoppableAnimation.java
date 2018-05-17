package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Class for decorator of animation that has key that stops them.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed;
    private boolean stop;

    /**
     * Constructor for the decorator.
     * @param sensor keyboard sensor.
     * @param key pressed key.
     * @param animation animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.key = key;
        this.keyboardSensor = sensor;
        this.isAlreadyPressed = true;
        this.stop = false;
    }

    /**
     * doOneFrame.
     * Method to do one frame of the animation.
     *
     * @param dt 1/ framesPerSecond.
     * @param d  draw surface.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.animation.doOneFrame(d, dt);
        if (this.keyboardSensor.isPressed(this.key)) {
            if (!this.isAlreadyPressed) {
                this.stop = true;
            }
        }
        if (!this.keyboardSensor.isPressed(this.key)) {
            isAlreadyPressed = false;
        }
    }

    /**
     * shouldStop.
     * The method returns false if the animation should not stop and
     * true if it does.
     *
     * @return true or false.
     */
    public boolean shouldStop() {
        if (this.stop) {
            this.stop = false;
            return true;
        }
        return false;
    }

}