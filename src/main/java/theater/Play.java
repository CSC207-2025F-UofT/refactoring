package theater;

/**
 * Class representing a play.
 */
public class Play {

    private final String name;
    private final String type;

    /**
     * Create a new play with a given name and type.
     *
     * @param name the play name
     * @param type the play type (for example, tragedy or comedy)
     */
    public Play(String name, String type) {
        this.name = name;
        this.type = type;
    }

    /**
     * Return the play name.
     *
     * @return the play name
     */
    public String getName() {
        return name;
    }

    /**
     * Return the play type.
     *
     * @return the play type
     */
    public String getType() {
        return type;
    }
}
