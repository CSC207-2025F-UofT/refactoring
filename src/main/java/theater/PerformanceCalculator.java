package theater;

/**
 * Calculator for determining the amount and volume credits for a performance.
 */
public class PerformanceCalculator {
    private final Performance performance;
    private final Play play;

    /**
     * Create a calculator for the given performance and play.
     *
     * @param performance the performance being calculated
     * @param play the play associated with the performance
     */
    public PerformanceCalculator(Performance performance, Play play) {
        this.performance = performance;
        this.play = play;
    }

    /**
     * Compute the charge amount for this performance.
     *
     * @return the amount in cents
     * @throws RuntimeException if the play type is unknown
     */
    public int getAmount() {
        int thisAmount = 0;
        switch (play.getType()) {
            case Constants.PLAY_TYPE_TRAGEDY:
                thisAmount = Constants.TRAGEDY_BASE_AMOUNT;
                if (performance.getAudience() > Constants.TRAGEDY_AUDIENCE_THRESHOLD) {
                    thisAmount += Constants.TRAGEDY_EXTRA_AMOUNT
                            * (performance.getAudience() - Constants.TRAGEDY_AUDIENCE_THRESHOLD);
                }
                break;
            case Constants.PLAY_TYPE_COMEDY:
                thisAmount = Constants.COMEDY_BASE_AMOUNT;
                if (performance.getAudience() > Constants.COMEDY_AUDIENCE_THRESHOLD) {
                    thisAmount += Constants.COMEDY_OVER_BASE_CAPACITY_AMOUNT
                            + (Constants.COMEDY_OVER_BASE_CAPACITY_PER_PERSON
                            * (performance.getAudience() - Constants.COMEDY_AUDIENCE_THRESHOLD));
                }
                thisAmount += Constants.COMEDY_AMOUNT_PER_AUDIENCE * performance.getAudience();
                break;
            default:
                throw new RuntimeException("unknown type: " + play.getType());
        }
        return thisAmount;
    }

    /**
     * Compute the volume credits earned for this performance.
     *
     * @return the number of volume credits
     */
    public int getVolumeCredits() {
        int result = Math.max(performance.getAudience() - Constants.BASE_VOLUME_CREDIT_THRESHOLD, 0);
        if (Constants.PLAY_TYPE_COMEDY.equals(play.getType())) {
            result += performance.getAudience() / Constants.COMEDY_EXTRA_VOLUME_FACTOR;
        }
        return result;
    }
}
