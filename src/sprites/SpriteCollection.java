package sprites;

import biuoop.DrawSurface;

import java.util.ArrayList;

/**
 * Created by Eilon.
 * ID: 308576933.
 * Class for SpriteCollection.
 * Holds the all the collidables who implements sprite.
 */
public class SpriteCollection {
    //Members
    private ArrayList<Sprite> spritesArray;

    /**
     * Constructor for SpriteCollection, creates new list.
     */
    public SpriteCollection() {
        this.spritesArray = new ArrayList<Sprite>();
    }

    /**
     * add the given sprite to the list.
     *
     * @param s sprite object.
     */
    public void addSprite(Sprite s) {
        this.spritesArray.add(s);
    }

    /**
     * removes the given sprite to the list.
     *
     * @param s sprite object.
     */
    public void removeSprite(Sprite s) {
        this.spritesArray.remove(s);
    }

    /**
     * call timePassed() on all sprites.
     * @param dt 1/framesPerSecond
     */
    public void notifyAllTimePassed(double dt) {
        for (int i = 0; i < spritesArray.size(); i++) {
            this.spritesArray.get(i).timePassed(dt);
        }
    }

    /**
     * call drawOn() on all sprites.
     *
     * @param d surface.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : spritesArray) {
            s.drawOn(d);
        }
    }
}