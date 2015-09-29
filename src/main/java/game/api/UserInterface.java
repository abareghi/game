package game.api;

/**
 * Abstracts interactions with end user.
 */
public interface UserInterface {

    /**
     * Shows given choices to end user and takes its input. It checks this input to be valid, i.e.
     * it's is among given choices codes.
     *
     * @param choices given array of choices to be shown to end user, not null
     * @return the code of choice which was selected by user, no null
     */
    String interact(Choice... choices);

    /**
     * Show a message to user.
     *
     * @param message the message to be shown to user
     */
    void show(String message);

    /**
     * Reads the input from end user.
     *
     * @return reads user's input
     */
    String readInput();
}
