package theater;

/**
 * Represents a play with a name and type.
 */
public class Play {

    private final String name;
    private final String type;

    public Play(String name, String type) {
        this.name = name;
        this.type = type;
    }

    /**
     * Return the name of this play.
     *
     * @return the name of this play
     */

    public String getName() {
        return this.name;
    }

    /**
     * Return the type of this play.
     *
     * @return the type of this play
     */
    public String getType() {
        return this.type;
    }
}
