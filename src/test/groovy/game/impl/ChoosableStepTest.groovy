package game.impl

import game.api.Choice
import game.api.UserInterface
import spock.lang.Specification

/**
 */
class ChoosableStepTest extends Specification {
    def "execute"() {
        given:
        def userInterface = Mock(UserInterface)
        def choice = new Choice("1", "Choice1")
        def step = new ChoosableStep("Sample Narration", choice)

        when:
        step.execute(userInterface)

        then:
        1 * userInterface.interact(choice)
    }
}
