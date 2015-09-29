package game.api;

/**
 * Provides functionality for persisting the game topics.
 */
public interface Persistence {
    /**
     * Saves the given topic.
     *
     * @param topic the given topic, not null
     */
    void save(Topic topic);

    /**
     * Loads a topic from game persistence store.
     *
     * @return the topic, if any
     */
    Topic load();
}
