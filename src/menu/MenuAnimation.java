package menu;


import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;


/**
 * Class for the menu animation.
 */
public class MenuAnimation implements Menu<Task<Void>> {
    //Members.
    private List<TaskInformationHolder> tasksList;
    private Task<Void> status;
    private KeyboardSensor ks;
    private boolean stop;
    private Image img;


    /**
     * Constructor.
     *
     * @param ks keyboard sensor.
     */
    public MenuAnimation(KeyboardSensor ks) {
        this.tasksList = new LinkedList<TaskInformationHolder>();
        this.ks = ks;
        this.stop = false;
        this.img = null;


    }

    /**
     * add tasks to the menu.
     *
     * @param key       key to press.
     * @param message   to show on animation.
     * @param returnVal return value.
     */
    public void addSelection(String key, String message, Task<Void> returnVal) {
        TaskInformationHolder task = new TaskInformationHolder(key, message, returnVal);
        this.tasksList.add(task);
    }

    /**
     * get status of the menu, which task is on now.
     *
     * @return the current status.
     */
    public Task<Void> getStatus() {
        return this.status;
    }

    /**
     * doOneFrame.
     * Method to do one frame of the animation.
     *
     * @param dt 1/framesPerSecond.
     * @param d  draw surface.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        int width = d.getWidth();
        int height = d.getHeight();
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, width, height);
        this.drawLogo(d);
        d.setColor(Color.WHITE);
        d.drawText((int) (width * 0.28), (int) (height * 0.22), this.menuString(), 50);
        int i = 0;
        for (TaskInformationHolder mt : tasksList) {
            String message = mt.getMessage();
            String key = mt.getKey();
            d.drawText((int) (width * 0.35), (int) (height * 0.4) + (i * 31), "(" + key + ") " + message, 40);
            i += 2;
        }
        for (TaskInformationHolder mt : tasksList) {
            if (ks.isPressed(mt.getKey())) {
                this.status = mt.getReturnVal();
                this.stop = true;
            }
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

    /**
     * draw logo of the game "arkanoid".
     *
     * @param d draw surface.
     */
    public void drawLogo(DrawSurface d) {
        if (this.img == null) {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("screens_images/arkanoid.jpg");
            try {
                this.img = ImageIO.read(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (this.img != null) {
            d.drawImage((int) (d.getWidth() * 0.2), (int) (d.getHeight() * 0.12), this.img);
        }

    }

    /**
     * menuString.
     *
     * @return string of the headline in the menu.
     */
    public String menuString() {
        return "";
    }

    /**
     * add sub menu to the main maenu.
     *
     * @param key     key to press.
     * @param messgae to show on screen.
     * @param subMenu the sub menu.
     */
    public void addSubMenu(String key, String messgae, Menu<Task<Void>> subMenu) {
        this.addSelection(key, messgae, subMenu.getStatus());
    }

    /**
     * Cleans the tasks list.
     */
    public void cleanTasks() {
        this.tasksList.clear();
    }
}