package menu;

/**
 * Class that holds task's information.
 */
public class TaskInformationHolder {
    //Members.
    private String key;
    private String message;
    private Task<Void> returnVal;

    /**
     * Consturctor.
     *
     * @param key       key to press.
     * @param message   message to show.
     * @param returnVal return value.
     */
    public TaskInformationHolder(String key, String message, Task<Void> returnVal) {
        this.key = key;
        this.message = message;
        this.returnVal = returnVal;
    }

    /**
     * getKey.
     *
     * @return returns task's key.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * getMessage.
     *
     * @return task's message.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * getReturnVal.
     *
     * @return task's type.
     */
    public Task<Void> getReturnVal() {
        return this.returnVal;
    }
}