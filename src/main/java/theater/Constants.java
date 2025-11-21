package theater;

/**
 * Constants used in this program.
 */
public final class Constants {

    // volume constants
    /** Base threshold for volume credits. */
    public static final int BASE_VOLUME_CREDIT_THRESHOLD = 30;
    /** Extra volume factor for comedy plays. */
    public static final int COMEDY_EXTRA_VOLUME_FACTOR = 5;

    // comedy amount constants
    /** Amount per audience member for comedy plays. */
    public static final int COMEDY_AMOUNT_PER_AUDIENCE = 300;
    /** Audience threshold for comedy plays. */
    public static final int COMEDY_AUDIENCE_THRESHOLD = 20;
    /** Base amount for comedy plays. */
    public static final int COMEDY_BASE_AMOUNT = 30000;
    /** Additional amount for comedy plays over capacity. */
    public static final int COMEDY_OVER_BASE_CAPACITY_AMOUNT = 10000;
    /** Per person amount for comedy plays over capacity. */
    public static final int COMEDY_OVER_BASE_CAPACITY_PER_PERSON = 500;

    // tragedy amount constants
    /** Audience threshold for tragedy plays. */
    public static final int TRAGEDY_AUDIENCE_THRESHOLD = 30;
    /** Base amount for tragedy plays. */
    public static final int TRAGEDY_BASE_AMOUNT = 40000;
    /** Per person amount for tragedy plays over capacity. */
    public static final int TRAGEDY_OVER_BASE_CAPACITY_PER_PERSON = 1000;

    // formatting constants
    /** Factor used for percentage calculations. */
    public static final int PERCENT_FACTOR = 100;

    // history constants
    /** Base amount for history plays. */
    public static final int HISTORY_BASE_AMOUNT = 20000;
    /** Per person amount for history plays over capacity. */
    public static final int HISTORY_OVER_BASE_CAPACITY_PER_PERSON = 1000;
    /** Audience threshold for history plays. */
    public static final int HISTORY_AUDIENCE_THRESHOLD = 20;
    /** Volume credit threshold for history plays. */
    public static final int HISTORY_VOLUME_CREDIT_THRESHOLD = 20;

    // pastoral constants
    /** Base amount for pastoral plays. */
    public static final int PASTORAL_BASE_AMOUNT = 40000;
    /** Per person amount for pastoral plays over capacity. */
    public static final int PASTORAL_OVER_BASE_CAPACITY_PER_PERSON = 2500;
    /** Audience threshold for pastoral plays. */
    public static final int PASTORAL_AUDIENCE_THRESHOLD = 20;
    /** Volume credit threshold for pastoral plays. */
    public static final int PASTORAL_VOLUME_CREDIT_THRESHOLD = 20;

    private Constants() {
        // Private constructor to prevent instantiation
    }
}