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
     */
    public static AbstractPerformanceCalculator createPerformanceCalculator(Performance performance, Play play) {
        return null;
    }
}
