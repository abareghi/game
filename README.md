# RPG
This is a simple role-playing game.
Available use-stories are:
    - Player can play a topic
    - Player can create a character
    - Player can explore in a topic
    - Player can fight in a step within a topic
    - Player can gain experience through fighting
    - Player can save a game
    - Player can load a game

- NOTE
    - In current implementation, a rudimentary console based user interface is used. However, it can easily be replace by a better
        implementation of "UserInterface".
    - New Topics could be added to system by implementing "Topic" and extending TopicRepository to register and retrieve topics.
    - Current game topic, "HarryPotterTopic" could be improved to externalized the definition/creation of steps, their logic and
        transition between them. In that case, even it may be possible to have a general implementation of "Topic" which can dynamically
        load from a definition markup/markdown file.
    - In this implementation, it was not supposed to used any library/framework other that java. However, a dependency injection framework
        would ease the life.
    - In order to check code quality, jacoco-maven-plugin is configured to gather the test coverage and a few rules are configured.
        It is also possible to feed these data to sonar for further check, if needed.

- mvn clean package to build the project
    - It will create an executable fat jar in target folder
    - This executable takes no parameters.
    - In case it needs of saving a game, it will be saved in execution directory in the flat text file
    - The program will overwrite an existing file, if exist.
    - It will also logs to the file "rpgGame.log" in the same folder of executing program.

