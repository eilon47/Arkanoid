package menu;

import animations.Animation;

/**
 * Class for Menu interface.
 * @param <T> type of tasks.
 */
public interface Menu<T> extends Animation {
    /**
     * addselcetion.
     *
     * @param key       key to press.
     * @param message   to show on animation.
     * @param returnVal return value.
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * get status of the menu.
     *
     * @return the current status.
     */
    T getStatus();

    /**
     * add sub menu to the main maenu.
     *
     * @param key     key to press.
     * @param message to show on animation.
     * @param subMenu the sub menu.
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);

    /**
     * Cleans the task's list.
     */
    void cleanTasks();
}