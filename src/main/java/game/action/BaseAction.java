package game.action;

import game.api.Choice;
import game.api.UserInterface;

/**
 * An abstraction over actions that can be done in game's menu items
 */
public abstract class BaseAction {
    protected UserInterface userInterface;
    protected Choice[] choices;
    protected game.api.Character character;

    /**
     * Shows choices available for this menu item, gets user input and then calls defined logic for
     * choice of that input.
     */
    public void execute() {
        String exitChoice = getExitChoiceCode();
        while (true) {
            String userInput = userInterface.interact(choices);

            userInterface.show("Your choice was: " + userInput);
            if (exitChoice.equals(userInput)) {
                userInterface.show("Returning to previous state ....");
                break;
            }
            processChoice(userInput);
        }
    }

    /**
     * Returns the character of the game.
     *
     * @return the character of the game
     */
    public game.api.Character getCharacter() {
        return character;
    }

    /**
     * Setter for character
     *
     * @param character the given character, not null
     */
    public void setCharacter(game.api.Character character) {
        this.character = character;
    }

    private void processChoice(String userInput) {
        for (Choice choice : choices) {
            if (choice.getCode().equals(userInput)) {
                choice.getCommand().execute();
            }
        }
    }

    private String getExitChoiceCode() {
        for (Choice choice : choices) {
            if (choice.isExit()) {
                return choice.getCode();
            }
        }

        throw new RuntimeException("There is no exit choice defined");
    }
}
