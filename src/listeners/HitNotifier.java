package listeners;

/**
 * HitNotifier interface.
 * The class is for objects that when they get hit they can "tell" that
 * to all their HitListeners.
 */
public interface HitNotifier {
    /**
     * addHitListener.
     * Add HitListener to the list so whenever there is a hit the hit listener know about it.
     *
     * @param hl HitListener.
     */
    void addHitListener(HitListener hl);

    /**
     * removeHitListener.
     * Remove listener from the list.
     *
     * @param hl HitListener.
     */
    void removeHitListener(HitListener hl);
}