package game.impl

import game.api.Character
import game.api.Topic
import spock.lang.Specification

/**
 */
class FileBasedPersistenceTest extends Specification {
    def "save"() {
        given:
        def repository = new TopicRepository()
        def persistence = new FileBasedPersistence(repository)
        def character = new Character("name", 5)
        def topic = Mock(Topic)
        topic.character >> character
        topic.name >> "topicName"

        when:
        persistence.save(topic)
        def loadedTopic = persistence.load()

        then:
        loadedTopic.name == topic.name
        loadedTopic.character.name == character.name
        loadedTopic.character.experience == character.experience
    }

    def "load"() {
        given:
        def repository = new TopicRepository()
        def persistence = new FileBasedPersistence(repository)
        def topicName = 'topicName'
        def characterName = 'name'
        def experience = 5
        def file = new File("savedGame.rpg")
        file.deleteOnExit()
        file.append "$topicName\n\t$characterName\n\t$experience"

        when:
        def loadedTopic = persistence.load()

        then:
        loadedTopic.name == topicName
        loadedTopic.character.name == characterName
        loadedTopic.character.experience == experience
    }
}
