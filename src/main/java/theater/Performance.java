package theater;

/**
 * Class representing a performance of a play.
 */
public class Performance {

    private final String playID;
    private final int audience;

    public Performance(String playID, int audience) {
        this.playID = playID;
        this.audience = audience;
    }

    /**
     * Return the play ID of this performance.
     */
    @SuppressWarnings({"checkstyle:JavadocMethod", "checkstyle:SuppressWarnings"})
    public String getPlayID() {
        return this.playID;
    }

    /**
     * Return the audience size for this performance.
     */
    public int getAudience() {
        return this.audience;
    }
}
