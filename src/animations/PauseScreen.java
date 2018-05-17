package animations;
import biuoop.DrawSurface;
import sprites.SpriteCollection;
import java.awt.Color;

/**
 * PauseScreen class.
 * Animation for the game when the user choose to pause it.
 * Created by Eilon.
 */
public class PauseScreen implements Animation {
    //Members.
    private SpriteCollection sprites;
    private boolean stop;

    /**
     * Constructor.
     *@param sprites sprites from game.
     */
    public PauseScreen(SpriteCollection sprites) {
        this.sprites = sprites;
        this.stop = false;
    }

    /**
     * doOneFrame.
     *
     * @param dt 1 / framespersecond
     * @param d draw surface.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        sprites.drawAllOn(d);
        d.setColor(Color.WHITE);
        d.fillRectangle(50, (d.getHeight() / 2) - 42, d.getWidth() - 100, 50);
        d.setColor(Color.BLACK);
        d.drawText(50, d.getHeight() / 2, "Paused, press space to continue", 48);
    }

    /**
     * shouldStop.
     *
     * @return true or false.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}