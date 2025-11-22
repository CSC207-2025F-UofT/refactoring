package theater;

/**
 * Nothing.
 */
public class PerformanceData {
    private final Performance performance;
    private final Play play;

    public PerformanceData(Performance performance, Play play) {
        this.performance = performance;
        this.play = play;
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
    public int amountFor() {
        int result = 0;
        switch (getType()) {
            case "tragedy":
                result = Constants.PASTORAL_BASE_AMOUNT;
                if (performance.getAudience() > Constants.TRAGEDY_AUDIENCE_THRESHOLD) {
                    result += Constants.TRAGEDY_OVER_BASE_CAPACITY_PER_PERSON
                            * (performance.getAudience() - Constants.TRAGEDY_AUDIENCE_THRESHOLD);
                }
                break;
            case "comedy":
                result = Constants.COMEDY_BASE_AMOUNT;
                if (performance.getAudience() > Constants.COMEDY_AUDIENCE_THRESHOLD) {
                    result += Constants.COMEDY_OVER_BASE_CAPACITY_AMOUNT
                            + (Constants.COMEDY_OVER_BASE_CAPACITY_PER_PERSON
                            * (performance.getAudience() - Constants.COMEDY_AUDIENCE_THRESHOLD));
                }
                result += Constants.COMEDY_AMOUNT_PER_AUDIENCE * performance.getAudience();
                break;
            default:
                throw new RuntimeException(String.format("unknown type: %s", getType()));
        }
        return result;
    }

    /**
     * Nothing.
     * @return n
     */
    public int volumeCredits() {
        int result = Math.max(getAudience() - Constants.BASE_VOLUME_CREDIT_THRESHOLD, 0);
        // add extra credit for every five comedy attendees
        if ("comedy".equals(getType())) {
            result += getAudience() / Constants.COMEDY_EXTRA_VOLUME_FACTOR;
        }
        return result;
    }
}
