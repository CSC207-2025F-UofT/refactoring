package theater;

/**
 * Class representing a performance of a play.
 */
public class Performance {

    private final String playID;
    private final int audience;

    /**
     * Create a new performance.
     *
     * @param playID   the identifier for the play
     * @param audience the size of the audience
     */
    public Performance(String playID, int audience) {
        this.playID = playID;
        this.audience = audience;
    }

    /**
     * Return the identifier of the play.
     *
     * @return the play identifier
     */
    public String getPlayID() {
        return playID;
    }

    /**
     * Return the audience size.
     *
     * @return the audience size
     */
    public int getAudience() {
        return audience;
    }
}
