package theater;

/**
 * Computed data for a performance used in rendering statements.
 */
public class PerformanceData {
    private final Performance performance;
    private final Play play;
    private final int amount;
    private final int volumeCredits;

    public PerformanceData(Performance performance, Play play,
                          AbstractPerformanceCalculator calculator) {
        this.performance = performance;
        this.play = play;
        this.amount = calculator.getAmount();
        this.volumeCredits = calculator.getVolumeCredits();
    }

    public int getAudience() {
        return performance.getAudience();
    }

    public String getName() {
        return play.getName();
    }

    public String getType() {
        return play.getType();
    }

    public int getAmount() {
        return amount;
    }

    public int getVolumeCredits() {
        return volumeCredits;
    }
}
