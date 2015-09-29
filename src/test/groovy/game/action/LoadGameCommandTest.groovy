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
class LoadGameCommandTest extends Specification {
    def "execute "() {
        given: "User has an already saved the game"
        def character = new Character('name', 10)
        def topic = prepareTopic(character)
        def userInterface = Mock(UserInterface)
        def persistence = [load: { topic }] as Persistence
        MainGameAction parent = new MainGameAction(userInterface, [] as TopicRepository, persistence, [] as CreateCharacterAction, [] as TopicExecutor)
        def command = new MainGameAction.LoadGameCommand(userInterface, persistence, parent)

        when: "user wants to load it"
        command.execute()

        then: "a message is shown to user and game is loaded"
        1 * userInterface.show(_ as String)
        parent.topic == topic
        parent.topic.character.name == character.name
        parent.topic.character.experience == character.experience
    }

    private static Topic prepareTopic(Character character) {
        return [
                getCharacter: { return character }
        ] as Topic
    }
}
