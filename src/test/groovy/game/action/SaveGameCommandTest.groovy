package game.action

import game.api.Persistence
import game.api.Topic
import game.api.UserInterface
import game.impl.TopicExecutor
import game.impl.TopicRepository
import spock.lang.Specification

/**
 */
class SaveGameCommandTest extends Specification {
    def "execute "() {
        given: "User intends to save the game"
        def userInterface = Mock(UserInterface)
        def persistence = Mock(Persistence)
        MainGameAction parent = new MainGameAction(userInterface, [] as TopicRepository, persistence, [] as CreateCharacterAction, [] as TopicExecutor)
        def topic = Mock(Topic)
        parent.setTopic(topic)
        def command = new MainGameAction.SaveGameCommand(userInterface, persistence, parent)

        when: "user wants to save the game"
        command.execute()

        then: "a message is shown to user and game is saved"
        1 * userInterface.show(_ as String)
        1 * persistence.save(topic)
    }

    def "execute when user wants to save a game without started it before"() {
        given: "User has not started a game yet"
        def userInterface = Mock(UserInterface)
        def persistence = Mock(Persistence)
        MainGameAction parent = new MainGameAction(userInterface, [] as TopicRepository, persistence, [] as CreateCharacterAction, [] as TopicExecutor)
        def command = new MainGameAction.SaveGameCommand(userInterface, persistence, parent)

        when: "user wants to save the game"
        command.execute()

        then: "proper messages are shown to user and nothing else has happened"
        2 * userInterface.show(_ as String)
        0 * persistence.save(_ as Topic)
    }
}
