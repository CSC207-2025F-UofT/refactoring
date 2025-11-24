package theater;

/**
 * Represents a play with a name and type (such as tragedy or comedy).
 */

public class Play {

    private String name;
    private String type;

    public Play(String name, String type) {
        this.name = name;
        this.setType(type);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
