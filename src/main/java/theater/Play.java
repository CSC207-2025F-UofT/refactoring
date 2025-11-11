package theater;

/**
 * Class representing a play.
 */
public class Play {

    private String name;
    private String type;

    public Play(String name, String type) {
        this.name = name;
        this.type = type;
    }

    /**
     * Gets the name of the play.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the type of the play.
     * @return the type
     */
    public String getType() {
        return type;
    }
}
