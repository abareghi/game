package game.action;

import game.api.Character;
import game.api.Choice;
import game.api.Command;
import game.api.UserInterface;

/**
 * An action to process user choices for creating a character.
 * Choices are:
 * 1 - Given a name
 * 2- Return
 */
public class CreateCharacterAction extends BaseAction {

    /**
     * Creates an instance with given info.
     *
     * @param userInterface the inctance of {@link UserInterface} , not null
     */
    public CreateCharacterAction(final UserInterface userInterface) {
        this.userInterface = userInterface;
        this.choices = new Choice[]{
                new Choice("1", "Give a name.", new GiveNameCommand(userInterface, this)),

                new Choice("2", "Return", true, new ReturnCommand(userInterface))};
    }

    private static class ReturnCommand implements Command {
        private final UserInterface userInterface;

        private ReturnCommand(UserInterface userInterface) {
            this.userInterface = userInterface;
        }

        @Override
        public void execute() {
            userInterface.show("Let's return back to previous menu");
        }
    }

    private static class GiveNameCommand implements Command {
        private final UserInterface userInterface;
        private final CreateCharacterAction parent;

        private GiveNameCommand(UserInterface userInterface, CreateCharacterAction parent) {
            this.userInterface = userInterface;
            this.parent = parent;
        }

        @Override
        public void execute() {
            userInterface.show("let's give a name");
            String characterName = userInterface.readInput(); // needs validation
            parent.character = new Character(characterName);
        }
    }
}
