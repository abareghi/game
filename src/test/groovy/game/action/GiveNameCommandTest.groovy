package game.action

import game.api.UserInterface
import spock.lang.Specification

/**
 */
class GiveNameCommandTest extends Specification {
    def "execute"() {
        given:
        def userInterface = Mock(UserInterface)
        CreateCharacterAction parent = new CreateCharacterAction(userInterface)
        def command = new CreateCharacterAction.GiveNameCommand(userInterface, parent)

        when:
        command.execute()

        then:
        1 * userInterface.show(_ as String)
        1 * userInterface.readInput()
        parent.character != null
    }
}
