package game.impl;

import game.api.Choice;
import game.api.Step;

/**
 * An abstract class to keep common logic of steps.
 */
public abstract class BaseStep implements Step {

    protected String narration;
    protected Choice[] choices;

    /**
     * Creates an instance with given info.
     *
     * @param narration the given narration for this step
     * @param choices   the available choices for this step, not null
     */
    protected BaseStep(String narration, Choice[] choices) {
        this.narration = narration;
        this.choices = choices;
    }

    @Override
    public String getNarration() {
        return narration;
    }
}
