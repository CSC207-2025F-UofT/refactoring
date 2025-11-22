package theater;

/**
 * N.
 */
public abstract class AbstractPerformanceCalculator {

    private final Performance performance;
    private final Play play;

    public AbstractPerformanceCalculator(Performance performance, Play play) {
        this.performance = performance;
        this.play = play;
    }

    public Play getPlay() {
        return play;
    }

    public Performance getPerformance() {
        return performance;
    }

    /**
     * N.
     * @param performance .
     * @param play .
     * @return .
     * @throws RuntimeException .
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

    /**
     * Nothing.
     * @return nothing
     * @throws RuntimeException throws unknow type error
     */
    public abstract int amountFor();

    /**
     * Nothing.
     * @return n
     */
    public int volumeCredits() {
        return Math.max(performance.getAudience() - Constants.BASE_VOLUME_CREDIT_THRESHOLD, 0);
    }
}
