package game.impl

import game.api.Choice
import spock.lang.Specification

/**
 */
class ConsoleUserInterfaceTest extends Specification {
    def "interact"() {
        given:
        def output = new ByteArrayOutputStream()
        def console = Mock(BufferedReader)
        def userInterface = new ConsoleUserInterface(new PrintStream(output), console)
        def choiceText = 'First Choice'
        def choiceCode = '1'
        console.readLine() >> choiceCode

        when:
        def result = userInterface.interact(new Choice(choiceCode, choiceText))

        then:
        def outputString = output.toString()
        outputString.contains(choiceText)
        result.trim() == choiceCode

    }

    def "Show"() {
        given:
        def output = new ByteArrayOutputStream()
        def console = Mock(BufferedReader)
        def message = 'hello there'
        def userInterface = new ConsoleUserInterface(new PrintStream(output), console)

        when:
        userInterface.show(message)

        then:
        output.toString().trim() == message.trim()
    }

    def "readInput"() {
        given:
        def output = new ByteArrayOutputStream()
        def console = Mock(BufferedReader)
        def input = 'this is input'
        console.readLine() >> input
        def userInterface = new ConsoleUserInterface(new PrintStream(output), console)

        when:
        def result = userInterface.readInput()

        then:
        result.trim() == input.trim()
    }
}
