package game.impl;

import game.api.Choice;
import game.api.UserInterface;

/**
 * Represents a type of steps in {@link game.api.Topic} with a given character
 * which can gain fight and gain experience.
 */
public class FightStep extends BaseStep {

    private static final int EXPERIENCE_TO_GAIN = 10;
    private game.api.Character character;

    /**
     * Creates an instance with given info.
     *
     * @param narration the narration to be used for this step, not null
     * @param character the character of the game to fight in this step
     */
    public FightStep(String narration, game.api.Character character) {
        super(narration, new Choice[]{new Choice("1", "Return")});
        this.character = character;
    }


    @Override
    public String execute(UserInterface userInterface) {
        userInterface.show("Your current experience is: " + character.getExperience());
        character.gainExperience(EXPERIENCE_TO_GAIN);
        userInterface.show("You gained " + EXPERIENCE_TO_GAIN + " through fighting!");
        return choices[0].getCode();
    }
}
