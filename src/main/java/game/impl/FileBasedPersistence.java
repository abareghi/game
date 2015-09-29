package game.impl;

import game.api.Character;
import game.api.Persistence;
import game.api.Topic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 * A file based implemetation of {@link Persistence}.
 */
public class FileBasedPersistence implements Persistence {

    private TopicRepository repository;

    /**
     * Creates an instance with given info.
     *
     * @param repository the topic repository, not null
     */
    public FileBasedPersistence(TopicRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Topic topic) {
        File file = new File("savedGame.rpg");
        StringBuffer record =
                new StringBuffer()
                        .append(topic.getName()).append('\n')
                        .append('\t').append(topic.getCharacter().getName()).append('\n')
                        .append("\t").append(topic.getCharacter().getExperience()).append('\n');

        try (PrintWriter writer = new PrintWriter(file)) {
            writer.println(record);

        } catch (Exception e) {
            throw new RuntimeException("Error happened while saving the topic:" + topic.getName());
        }
    }

    @Override
    public Topic load() {
        File file = new File("savedGame.rpg");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String topicName = reader.readLine().trim();
            String characterName = reader.readLine().trim();
            int characterExperience = Integer.valueOf(reader.readLine().trim());
            return repository
                    .getTopicInstance(topicName, new Character(characterName, characterExperience));

        } catch (Exception e) {
            throw new RuntimeException("Error happened while loading the topic");
        }
    }

}
