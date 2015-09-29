package game.action

import game.api.Persistence
import game.api.UserInterface
import game.impl.TopicExecutor
import game.impl.TopicRepository
import spock.lang.Specification

/**
 */
class CreateCharacterCommandTest extends Specification {
    def "execute"() {
        given:
        def userInterface = Mock(UserInterface)
        def createCharacterAction = Mock(CreateCharacterAction)
        MainGameAction parent = new MainGameAction(userInterface, [] as TopicRepository, [] as Persistence, createCharacterAction, [] as TopicExecutor)
        def command = new MainGameAction.CreateCharacterCommand(userInterface, createCharacterAction, parent)

        when:
        command.execute()

        then:
        1 * createCharacterAction.getCharacter()
        1 * userInterface.show(_ as String)
        1 * createCharacterAction.execute()
    }
}
