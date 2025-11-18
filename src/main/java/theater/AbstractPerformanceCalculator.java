package theater;

/**
 * Factory and holder for performance calculation data.
 */
public abstract class AbstractPerformanceCalculator {
    private final Performance performance;
    private final Play play;

    /**
     * Constructs a calculator for the given performance and play.
     *
     * @param performance the performance being evaluated
     * @param play the associated play
     */
    public AbstractPerformanceCalculator(Performance performance, Play play) {
        this.performance = performance;
        this.play = play;
    }

    /**
     * Factory for the appropriate calculator implementation for a performance/play pair.
     *
     * @param performance performance being evaluated
     * @param play associated play
     * @return calculator for the play type
     * @throws RuntimeException if the play type is unrecognized
     */
    public static AbstractPerformanceCalculator createPerformanceCalculator(Performance performance, Play play) {
        switch (play.getType()) {
            case "tragedy":
                return new TragedyCalculator(performance, play);
            case "comedy":
                return new ComedyCalculator(performance, play);
            case "history":
                return new HistoryCalculator(performance, play);
            case "pastoral":
                return new PastoralCalculator(performance, play);
            default:
                throw new RuntimeException(String.format("unknown type: %s", play.getType()));
        }
    }

    public Performance getPerformance() {
        return performance;
    }

    public Play getPlay() {
        return play;
    }

    /**
     * Calculates the amount owed for the performance.
     *
     * @return amount owed in cents
     */
    public abstract int getAmount();

    /**
     * Calculates the volume credits earned for the performance.
     *
     * @return earned volume credits
     */
    public int getVolumeCredits() {
        return Math.max(performance.getAudience() - Constants.BASE_VOLUME_CREDIT_THRESHOLD, 0);
    }
}
