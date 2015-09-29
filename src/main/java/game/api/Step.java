package game.api;

/**
 * Represents a step in a topic. Each step has a narration and a logic.
 */
public interface Step {
    /**
     * Returns the narration for this step.
     *
     * @return the narration of this step
     */
    String getNarration();

    /**
     * Executes the logic defined for this step.
     *
     * @param userInterface the given {@link UserInterface} to interact with end-user, if needed
     * @return the choice code selected by user, not null.
     */
    String execute(UserInterface userInterface);

}
