package theater;

/**
 * Nothing.
 */
public class PerformanceData {
    private final Performance performance;
    private final Play play;
    private final int amount;
    private final int volumeCredits;

    public PerformanceData(Performance performance, Play play, int amount, int volumeCredits) {
        this.performance = performance;
        this.play = play;
        this.amount = amount;
        this.volumeCredits = volumeCredits;
    }

    public int getAudience() {
        return performance.getAudience();
    }

    public String getType() {

        return play.getType();
    }

    public String getName() {

        return play.getName();
    }

    /**
     * Nothing.
     * @return nothing
     * @throws RuntimeException throws unknow type error
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Nothing.
     * @return n
     */
    public int getVolumeCredits() {
        return volumeCredits;
    }
}
