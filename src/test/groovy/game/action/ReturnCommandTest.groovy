package game.action

import game.api.UserInterface
import spock.lang.Specification

/**
 */
class ReturnCommandTest extends Specification {
    def "execute"() {
        given:
        def userInterface = Mock(UserInterface)
        def command = new CreateCharacterAction.ReturnCommand(userInterface)

        when:
        command.execute()

        then:
        1 * userInterface.show(_ as String)
    }
}
