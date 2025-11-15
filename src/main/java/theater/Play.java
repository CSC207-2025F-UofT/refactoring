package theater;

/**
 * Represents a play being performed at the theatre.
 * Contains the name and type of the play.
 */

public class Play {

    private String name;
    private String type;

    /**
     * Constructs a Play with the specified name and type.
     * @param name the type of name of the play
     * @param type the type or genre of the play
     */
    public Play(String name, String type) {
        this.setName(name);
        this.setType(type);
    }

    /**
     * Returns the name of the play.
     *
     * @return the play's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the play.
     *
     * @param name the new name of the play.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the type of play.
     * @return type the type of play.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of play.
     * @param type the new type for the play.
     */

    public void setType(String type) {
        this.type = type;
    }
}
