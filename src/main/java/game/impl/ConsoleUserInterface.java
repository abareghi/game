package game.impl;

import game.api.Choice;
import game.api.UserInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

/**
 * A command line based implementation of {@link UserInterface} to interact with user.
 */
public class ConsoleUserInterface implements UserInterface {

    private PrintStream writer;
    private BufferedReader console;

    /**
     * Creates an instance with given info.
     *
     * @param writer  the output instance to show messages to end user, not null
     * @param console the console to get input from end user, not null
     */
    public ConsoleUserInterface(PrintStream writer, BufferedReader console) {
        this.writer = writer;
        this.console = console;
    }

    @Override
    public String interact(Choice... choices) {
        Map<String, Choice> choiceMap = toMap(choices);
        String result;
        do {
            show(prepareMessage(choices));
            result = readInput();
            if (!choiceMap.containsKey(result)) {
                show("Invalid choice");
            } else {
                break;
            }

        } while (true);

        return result;
    }


    @Override
    public void show(String message) {
        writer.println(message);
    }

    @Override
    public String readInput() {
        writer.print("Your choice: ");
        try {
            return console.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String prepareMessage(Choice... choices) {
        StringBuilder result = new StringBuilder();
        for (Choice choice : choices) {
            result.append(choice.getCode()).append(" : ").append(choice.getMessage())
                    .append("\n");
        }

        return result.toString();
    }

    private Map<String, Choice> toMap(Choice... choices) {
        Map<String, Choice> result = new HashMap<>();
        for (Choice choice : choices) {
            result.put(choice.getCode(), choice);
        }
        return result;
    }
}
