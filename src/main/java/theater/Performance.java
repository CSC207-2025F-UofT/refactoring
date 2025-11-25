package theater;

/**
 * Class representing a performance of a play..
 */
public class Performance {

    private String playID;
    private int audience;

    public Performance(String playID, int audience) {
        this.playID = playID;
        this.audience = audience;
    }

    /**
     * Returns the playID of the performance as a String.
     * @return String playID
     */
    public String getplayID() {
        return playID;
    }

    public int getAudience() {
        return audience;
    }
}
