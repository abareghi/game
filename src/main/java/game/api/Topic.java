package game.api;

/**
 * Represents a topic in the game. Each topic has a start step followed by zero or more steps.
 * Topic knows how these steps are connected. Given the user choice in a specific step, topic
 * can tell if there is a next step to execute and what step is it.
 * A topic can't execute without a {@link Character}.
 */
public interface Topic {

    /**
     * Returns the narration, description or story about this topic to be intracted with end user.
     *
     * @return the narration of topic, not null
     */
    String getNarration();

    /**
     * Returns the name of the topic.
     *
     * @return the name of the topic, not null.
     */
    String getName();

    /**
     * Returns the start step of the topic.
     *
     * @return the start step of the topic, not null
     */
    Step getStartStep();

    /**
     * Checks if in current step for given user's choice, there is next step to be executed.
     *
     * @param userChoice the user's choice in current step, not null
     * @param current    the current step in topic, not null
     * @return true if there is a next step, false otherwise
     */
    boolean hasNextStep(String userChoice, Step current);

    /**
     * Returns next step for given user's choice in current step.
     *
     * @param userChoice the user's choice in current step, not null
     * @param current    the current step in topic, not null
     * @return the next step to be executed
     */
    Step getNextStep(String userChoice, Step current);

    /**
     * Returns the {@link Character} instance of this topic
     *
     * @return the instance of character in this topic
     */
    game.api.Character getCharacter();
}
