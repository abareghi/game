package game.impl

import game.api.Character
import game.api.UserInterface
import spock.lang.Specification

/**
 */
class FightStepTest extends Specification {
    def "execute"() {
        given:
        def character = new Character("character", 5)
        def userInterface = Mock(UserInterface)
        def fightStep = new FightStep("FightStepNarration", character)

        when:
        def result = fightStep.execute(userInterface)

        then:
        2 * userInterface.show(_ as String)
        character.experience == 15
        result == '1'
    }
}
