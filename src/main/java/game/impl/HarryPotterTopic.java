package game.impl;

import game.api.Character;
import game.api.Choice;
import game.api.Step;
import game.api.Topic;

import java.util.HashMap;
import java.util.Map;

/**
 * A dummy implementation fo {@link Topic} for 'Harry Potter' story.
 */
public class HarryPotterTopic implements Topic {

    private Map<Step, Map<String, Step>> stepMapping;
    private Step startStep;
    private String name;
    private game.api.Character character;

    /**
     * Creates an instance with given info.
     *
     * @param name      the name of this topic, not null
     * @param character the character to play this topic
     */
    public HarryPotterTopic(String name, Character character) {
        this.character = character;
        this.name = name;
        stepMapping = new HashMap<>();

        startStep = createChoosableStep(
                "You are about to start a mission to find an important object",
                new Choice("1", "North"),
                new Choice("2", "West"),
                new Choice("3", "South"),
                new Choice("4", "East"),
                new Choice("5", "Return")
        );
        Step northStep = createChoosableStep(
                "There is a big foe here, by fighting it you will gain experience",
                new Choice("1", "Fight"), new Choice("2", "Escape")
        );
        Step fightStep =
                new FightStep("You are brave enough to fight this foe. Good luck!", character);
        Step westStep = createChoosableStep(
                "There is nothing here, Sorry for your troubles",
                new Choice("1", "Return")
        );
        Step southStep = createChoosableStep(
                "There is nothing here, Sorry for your troubles",
                new Choice("1", "Return")
        );
        Step eastStep = createChoosableStep(
                "Congratulations! You found the object",
                new Choice("1", "Return")
        );


        addNewStep(startStep);
        addNewStep(northStep);
        addNewStep(fightStep);
        addNewStep(westStep);
        addNewStep(southStep);
        addNewStep(eastStep);

        addTransition(startStep, northStep, "1");
        addTransition(northStep, startStep, "2");
        addTransition(northStep, fightStep, "1");
        addTransition(fightStep, startStep, "1");

        addTransition(startStep, westStep, "2");
        addTransition(westStep, startStep, "1");

        addTransition(startStep, southStep, "3");
        addTransition(southStep, startStep, "1");

        addTransition(startStep, eastStep, "4");
    }

    @Override
    public String getNarration() {
        return "You are in a quest to find an important object";
    }

    public Step getStartStep() {
        return startStep;
    }

    @Override
    public boolean hasNextStep(String userChoice, Step current) {
        Map<String, Step> nextStepDefinition = stepMapping.get(current);  // NPE
        return nextStepDefinition.containsKey(userChoice);
    }

    @Override
    public Step getNextStep(String userChoice, Step current) {
        Map<String, Step> nextStepDefinition = stepMapping.get(current);  // NPE
        return nextStepDefinition.get(userChoice);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Character getCharacter() {
        return character;
    }

    private Map<String, Step> addNewStep(Step newStep) {
        if (!stepMapping.containsKey(newStep)) {
            stepMapping.put(newStep, new HashMap<String, Step>());
        }
        return stepMapping.get(newStep);
    }

    private void addTransition(Step from, Step to, String choice) {
        Map<String, Step> newStepMapping = addNewStep(from);
        addNewStep(to);
        newStepMapping.put(choice, to);
    }

    private ChoosableStep createChoosableStep(String narration, Choice... choices) {
        return new ChoosableStep(narration, choices);
    }
}
