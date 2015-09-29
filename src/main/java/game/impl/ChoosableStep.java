package game.impl;

import game.api.Choice;
import game.api.UserInterface;

/**
 * Represents a type of steps in {@link game.api.Topic} which only shows choices to user and
 * takes in its input.
 */
public class ChoosableStep extends BaseStep {

    /**
     * Create an instance with given info
     *
     * @param narration the narration to be used for this step, not null
     * @param choices   the list of choices to be shown to end user, not null
     */
    public ChoosableStep(String narration, Choice... choices) {
        super(narration, choices);
    }

    @Override
    public String execute(UserInterface userInterface) {
        return userInterface.interact(choices);
    }
}
