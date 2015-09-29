package game.action;

import game.api.Choice;
import game.api.Command;
import game.api.Persistence;
import game.api.Topic;
import game.api.UserInterface;
import game.impl.TopicExecutor;
import game.impl.TopicRepository;

/**
 * /**
 * An action to process user choices for game's main menu.
 * Choices are:
 * 1- Create a character
 * 2- Start
 * 3- Save
 * 4- Load
 * 5- Exit
 */
public class MainGameAction extends BaseAction {

    private Topic topic = null;

    /**
     * Creates an instance with given info.
     *
     * @param userInterface         the instance of {@link UserInterface}
     * @param topicRepository       the instance of {@link TopicRepository}
     * @param persistence           the instance of {@link Persistence}
     * @param createCharacterAction the instance of {@link CreateCharacterAction}
     * @param topicExecutor         the instance of {@link TopicExecutor}
     */
    public MainGameAction(final UserInterface userInterface, final TopicRepository topicRepository,
                          final Persistence persistence,
                          final CreateCharacterAction createCharacterAction,
                          final TopicExecutor topicExecutor) {

        this.userInterface = userInterface;
        this.choices = new Choice[]{
                new Choice("1", "Create a character.", new CreateCharacterCommand(userInterface, createCharacterAction, this)),

                new Choice("2", "Start", new StartGameCommand(userInterface, topicRepository, topicExecutor, this)),

                new Choice("3", "Save", new SaveGameCommand(userInterface, persistence, this)),

                new Choice("4", "Load", new LoadGameCommand(userInterface, persistence, this)),

                new Choice("5", "Exit.", true, new ExitGameCommand(userInterface))
        };
    }

    /**
     * Getter for topic.
     *
     * @return returns the topic for this class
     */
    public Topic getTopic() {
        return topic;
    }

    /**
     * Setter for topic.
     *
     * @param topic the instance of given topic, not null
     */
    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    private static class CreateCharacterCommand implements Command {
        private final UserInterface userInterface;
        private final CreateCharacterAction createCharacterAction;
        private final MainGameAction parent;

        private CreateCharacterCommand(UserInterface userInterface, CreateCharacterAction createCharacterAction, MainGameAction parent) {
            this.userInterface = userInterface;
            this.createCharacterAction = createCharacterAction;
            this.parent = parent;
        }

        @Override
        public void execute() {
            userInterface.show("let's create a character");
            createCharacterAction.execute();
            parent.setCharacter(createCharacterAction.getCharacter());
        }
    }

    private static class StartGameCommand implements Command {
        private final UserInterface userInterface;
        private final TopicRepository topicRepository;
        private final TopicExecutor topicExecutor;
        private final MainGameAction parent;

        private StartGameCommand(UserInterface userInterface, TopicRepository topicRepository, TopicExecutor topicExecutor,
                                 MainGameAction parent) {
            this.userInterface = userInterface;
            this.topicRepository = topicRepository;
            this.topicExecutor = topicExecutor;
            this.parent = parent;
        }

        @Override
        public void execute() {
            if (parent.getCharacter() == null) {
                userInterface.show("You can't start a game without having a character");
                return;
            }
            userInterface.show("Let's start the game");
            if (parent.getTopic() == null) {
                parent.setTopic(topicRepository.getTopicInstance("HarryPotter", parent.getCharacter()));
            }
            topicExecutor.execute(parent.getTopic());
        }
    }

    private static class SaveGameCommand implements Command {
        private final UserInterface userInterface;
        private final Persistence persistence;
        private final MainGameAction parent;

        private SaveGameCommand(UserInterface userInterface, Persistence persistence, MainGameAction parent) {
            this.userInterface = userInterface;
            this.persistence = persistence;
            this.parent = parent;
        }

        @Override
        public void execute() {
            userInterface.show("Let's save the game");
            if (parent.getTopic() == null) {
                userInterface.show("You can't save a game without starting/loading it");
                return;
            }
            persistence.save(parent.getTopic());
        }
    }

    private static class LoadGameCommand implements Command {
        private final UserInterface userInterface;
        private final Persistence persistence;
        private final MainGameAction parent;

        private LoadGameCommand(UserInterface userInterface, Persistence persistence, MainGameAction parent) {
            this.userInterface = userInterface;
            this.persistence = persistence;
            this.parent = parent;
        }

        @Override
        public void execute() {
            userInterface.show("Let's load a game");
            parent.setTopic(persistence.load());
            parent.setCharacter(parent.getTopic().getCharacter());
        }
    }

    private static class ExitGameCommand implements Command {
        private final UserInterface userInterface;

        private ExitGameCommand(UserInterface userInterface) {
            this.userInterface = userInterface;
        }

        @Override
        public void execute() {
            userInterface.show("Your choice is not valid");
        }
    }
}
