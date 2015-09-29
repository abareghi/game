package game.api;

/**
 * Represents a character in the game. This character has name and experience. It could be modified
 * or extended to add more features to character.
 */
public class Character {
    private String name;
    private int experience;

    /**
     * Creates a character with given name.
     *
     * @param name the name of character, not null
     */
    public Character(String name) {
        this.name = name;
    }

    /**
     * Creates a character with given name and experience.
     *
     * @param name       the name of character, not null
     * @param experience the amount of experience of this character
     */
    public Character(String name, int experience) {
        this.name = name;
        this.experience = experience;
    }

    /**
     * Adds given amount of experience to current character's experience.
     *
     * @param gainedExperience the amount of gained experience
     */
    public void gainExperience(int gainedExperience) {
        experience += gainedExperience;
    }

    /**
     * Getter of name.
     *
     * @return the name of character, not null
     */
    public String getName() {
        return name;
    }

    /**
     * Getter of experience.
     *
     * @return the amount of character's experience
     */
    public int getExperience() {
        return experience;
    }
}
