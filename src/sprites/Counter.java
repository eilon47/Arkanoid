package sprites;

/**
 * Counter class.
 * Class for counters from all kinds.
 */
public class Counter {
    private int counter;

    /**
     * Constructor.
     *
     * @param num number that the counter starts counting from.
     */
    public Counter(int num) {
        this.counter = num;
    }

    /**
     * increase.
     * add number to current count.
     *
     * @param number increases the counter in number.
     */
    public void increase(int number) {
        this.counter = this.counter + number;
    }

    /**
     * decrease.
     * subtract number to current count.
     *
     * @param number decreases the counter in number.
     */
    public void decrease(int number) {
        this.counter = this.counter - number;
    }

    /**
     * getValue.
     *
     * @return the current number of the counter.
     */
    public int getValue() {
        return this.counter;
    }
}