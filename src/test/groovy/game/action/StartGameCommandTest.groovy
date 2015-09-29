package game.action

import game.api.Character
import game.api.Persistence
import game.api.Topic
import game.api.UserInterface
import game.impl.TopicExecutor
import game.impl.TopicRepository
import spock.lang.Specification

/**
 */
class StartGameCommandTest extends Specification {
    def "execute after creating a character"() {
        given: "User has created a character"
        def userInterface = Mock(UserInterface)
        def topicRepository = new TopicRepository()
        def topicExecutor = Mock(TopicExecutor)
        MainGameAction parent = new MainGameAction(userInterface, topicRepository, [] as Persistence, [] as CreateCharacterAction, topicExecutor)
        def character = new Character('name')
        parent.setCharacter(character)
        def topic = Mock(Topic)
        parent.setTopic(topic)
        def command = new MainGameAction.StartGameCommand(userInterface, topicRepository, topicExecutor, parent)

        when: "user wants to start the game"
        command.execute()

        then: "game is started with MainGameAction topic"
        1 * userInterface.show(_ as String)
        1 * topicExecutor.execute(topic)
    }

    def "execute after creating a character but not played the topic yet"() {
        given: "User has created a character but does not have a topic yet"
        def userInterface = Mock(UserInterface)
        def topicRepository = new TopicRepository()
        def topicExecutor = Mock(TopicExecutor)
        MainGameAction parent = new MainGameAction(userInterface, topicRepository, [] as Persistence, [] as CreateCharacterAction, topicExecutor)
        def character = new Character('name')
        parent.setCharacter(character)
        def command = new MainGameAction.StartGameCommand(userInterface, topicRepository, topicExecutor, parent)

        when: "user wants to start the game"
        command.execute()

        then: "game is started and the topic is passed to MainGameAction as well as character"
        1 * userInterface.show(_ as String)
        1 * topicExecutor.execute(_ as Topic)
        parent.topic
        parent.topic.character.name == character.name

    }

    def "execute without creating a character"() {
        given: "User has not created a character yet"
        def userInterface = Mock(UserInterface)
        def topicRepository = Mock(TopicRepository)
        def topicExecutor = Mock(TopicExecutor)
        MainGameAction parent = new MainGameAction(userInterface, topicRepository, [] as Persistence, [] as CreateCharacterAction, topicExecutor)
        def command = new MainGameAction.StartGameCommand(userInterface, topicRepository, topicExecutor, parent)

        when: "user wants to start the game"
        command.execute()

        then: "a message is shown to user and nothing else has happened"
        1 * userInterface.show(_ as String)
        0 * topicRepository.getTopicInstance(_ as String, _ as Character)
        0 * topicExecutor.execute(_ as Topic)
    }
}
