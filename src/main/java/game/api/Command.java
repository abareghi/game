package game.api;

/**
 * Represents a piece of logic to be executed in the context of game.
 */
public interface Command {
    /**
     * Contains the actual logic of this class.
     */
    void execute();
}
