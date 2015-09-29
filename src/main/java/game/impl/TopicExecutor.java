package game.impl;

import game.api.Step;
import game.api.Topic;
import game.api.UserInterface;

/**
 * Executes a given topic. It start by topic's first step and then based on user input it will execute other steps as well.
 * This will continue until there is no more step remained based on user input.
 * It will show {@link game.api.Choice} for each step to user.
 */
public class TopicExecutor {
    private UserInterface userInterface;

    /**
     * Creates an instance with given info.
     *
     * @param userInterface the userInterface to interact with end user
     */
    public TopicExecutor(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    /**
     * Executes the given Topic.
     *
     * @param topic the given topic to be executed, not null
     */
    public void execute(Topic topic) {
        userInterface.show(topic.getName());
        userInterface.show(topic.getNarration());
        Step currentStep = topic.getStartStep();
        userInterface.show(currentStep.getNarration());
        String userInput = currentStep.execute(userInterface);

        while (topic.hasNextStep(userInput, currentStep)) {
            currentStep = topic.getNextStep(userInput, currentStep);
            String narration = currentStep.getNarration();
            userInterface.show(narration);
            userInput = currentStep.execute(userInterface);
        }
        userInterface.show("Exiting the topic ....");
    }
}
