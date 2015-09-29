package game.impl

import game.api.Step
import game.api.Topic
import game.api.UserInterface
import spock.lang.Specification

/**
 *
 */
class TopicExecutorTest extends Specification {
    def "execute"() {
        given:
        def userInterface = Mock(UserInterface)
        def topic = Mock(Topic)
        def startStep = Mock(Step)
        startStep.execute(userInterface) >> 'userChoice'
        def topicName = 'topicName'
        topic.name >> topicName
        def topicNarration = 'topicNarration'
        topic.narration >> topicNarration
        topic.startStep >> startStep
        topic.hasNextStep(_ as String, startStep) >> false
        def topicExecutor = new TopicExecutor(userInterface)

        when:
        topicExecutor.execute(topic)

        then:
        1 * topic.hasNextStep(_ as String, _ as Step)
        0 * topic.getNextStep(_ as String, _ as Step)
        1 * userInterface.show(topicNarration)
        1 * userInterface.show(topicName)
        1 * startStep.getNarration()
        1 * userInterface.show('Exiting the topic ....')
    }

    def "execute when has next step"() {
        given:
        def userInterface = Mock(UserInterface)
        def startStep = Mock(Step)
        def nextStep = Mock(Step)
        def userChoice = 'user Choice'
        def topic = prepareTopic('topicName', 'topicNarration', startStep, nextStep)
        nextStep.execute(_ as UserInterface) >> 'nothing'
        nextStep.getNarration() >> 'next step narration'
        startStep.execute(userInterface) >> userChoice
        def topicExecutor = new TopicExecutor(userInterface)

        when:
        topicExecutor.execute(topic)

        then:
        1 * startStep.getNarration()
        1 * nextStep.getNarration()
        1 * nextStep.execute(userInterface)
        1 * userInterface.show('Exiting the topic ....')

    }

    private static Topic prepareTopic(String name, String narration, Step startStep, Step nextStep) {
        return [
                getName     : { name },
                getNarration: { narration },
                getStartStep: { startStep },
                hasNextStep : { userInput, currentStep -> return currentStep == startStep },
                getNextStep : { userInput, currentStep -> return nextStep }
        ] as Topic
    }
}
