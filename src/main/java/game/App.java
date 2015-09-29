package game;

import game.action.CreateCharacterAction;
import game.action.MainGameAction;
import game.api.Persistence;
import game.api.UserInterface;
import game.impl.ConsoleUserInterface;
import game.impl.FileBasedPersistence;
import game.impl.TopicExecutor;
import game.impl.TopicRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.Console;
import java.io.PrintStream;

/**
 * Instantiates and executes game's main class. It also catches all exceptions in the system,
 * log them and exit the application gracefully.
 */
public class App {
    private static Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        Console console = System.console();
        PrintStream writer = System.out;
        UserInterface userInterface = new ConsoleUserInterface(writer, new BufferedReader(console.reader()));
        TopicRepository topicRepository = new TopicRepository();
        Persistence persistence = new FileBasedPersistence(topicRepository);
        CreateCharacterAction createCharacterAction = new CreateCharacterAction(userInterface);
        TopicExecutor topicExecutor = new TopicExecutor(userInterface);

        try {
            new MainGameAction(userInterface, topicRepository, persistence, createCharacterAction,
                    topicExecutor).execute();
        } catch (Exception e) {
            System.out.println("There seem to be a problem within the application. Exiting ...");
            log.error("A fatal error has happened. Exiting ...", e);
        }
    }
}
