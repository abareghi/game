package game.action

import game.api.UserInterface
import spock.lang.Specification

/**
 */
class ExitGameCommandTest extends Specification {
    def "execute"() {
        given:
        def userInterface = Mock(UserInterface)
        def command = new MainGameAction.ExitGameCommand(userInterface)

        when:
        command.execute()

        then:
        1 * userInterface.show(_ as String)
    }
}
