package game.api;

/**
 * Represents a choice within the application. A choice has a code, a message to be shown to user,
 * a flag showing if this choice is for existing current step and returning back to previous step
 * and a {@link Command} which contains the logic for this choice.
 */
public class Choice {
    private String code;
    private String message;
    private boolean exit;
    private Command command;

    /**
     * Creates a choce with given info.
     *
     * @param code    the given code for this choice, not null
     * @param message the given message for this choice, not null
     */
    public Choice(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Creates a choce with given info.
     *
     * @param code    the given code for this choice, not null
     * @param message the given message for this choice, not null
     * @param command the logic to be executed for this command, not null
     */
    public Choice(String code, String message, Command command) {
        this.code = code;
        this.message = message;
        this.command = command;
    }

    /**
     * Creates a choce with given info.
     *
     * @param code    the given code for this choice, not null
     * @param message the given message for this choice, not null
     * @param exit    true if this choice is for existing current step, false otherwise
     */
    public Choice(String code, String message, boolean exit) {
        this.code = code;
        this.message = message;
        this.exit = exit;
    }

    /**
     * Creates a choce with given info.
     *
     * @param code    the given code for this choice, not null
     * @param message the given message for this choice, not null
     * @param exit    true if this choice is for existing current step, false otherwise
     * @param command the logic to be executed for this command, not null
     */
    public Choice(String code, String message, boolean exit, Command command) {
        this.code = code;
        this.message = message;
        this.exit = exit;
        this.command = command;
    }

    /**
     * Getter for code.
     *
     * @return the code for this choice, not null
     */
    public String getCode() {
        return code;
    }

    /**
     * Getter for message.
     *
     * @return the message for this choice, not null
     */
    public String getMessage() {
        return message;
    }

    /**
     * Indicates if this choice is for returning to previous stage or not
     *
     * @return true if selecting this choice must return control to previous stage, false otherwise
     */
    public boolean isExit() {
        return exit;
    }

    /**
     * Returns the logic defined for this choice, if any
     *
     * @return the logic for this choice, if any is defined
     */
    public Command getCommand() {
        return command;
    }
}
