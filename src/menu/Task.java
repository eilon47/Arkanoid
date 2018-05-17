package menu;

/**
 * Task interface.
 * @param <T> type to return.
 */
public interface Task<T> {
    /**
     * runs the task.
     *
     * @return depends on type.
     */
    T run();
}