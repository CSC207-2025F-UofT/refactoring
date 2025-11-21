package theater;

/**
 * Class representing a performance of a play..
 */
public class Performance {

    private final int audience;
    private final String playID;

    public Performance(String playID, int audience) {
        this.playID = playID;
        this.audience = audience;
    }

    public String getPlayID() {
        return playID;
    }
}
