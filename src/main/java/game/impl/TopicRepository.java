package game.impl;

import game.api.Topic;

/**
 * A repository to keep all topics in the game. It return a new instance of the topic with given
 * name and character.
 */
public class TopicRepository {
    /**
     * Returns an instance of {@link Topic} wich given name and character.
     *
     * @param name      the given name, not null
     * @param character the given character, not null
     * @return a new instance of the topic with given name and character
     */
    public Topic getTopicInstance(String name, game.api.Character character) {
        return new HarryPotterTopic(name, character);
    }
}
