package theater;

/**
 * Calculator for pastoral performances.
 */
public class PastoralCalculator extends AbstractPerformanceCalculator {

    public PastoralCalculator(Performance performance, Play play) {
        super(performance, play);
    }

    @Override
    public int getAmount() {
        int result = Constants.PASTORAL_BASE_AMOUNT;
        if (getPerformance().getAudience() > Constants.PASTORAL_AUDIENCE_THRESHOLD) {
            result += Constants.PASTORAL_OVER_BASE_CAPACITY_PER_PERSON
                    * (getPerformance().getAudience() - Constants.PASTORAL_AUDIENCE_THRESHOLD);
        }
        return result;
    }

    @Override
    public int getVolumeCredits() {
        return Math.max(getPerformance().getAudience() - Constants.PASTORAL_VOLUME_CREDIT_THRESHOLD, 0)
                + getPerformance().getAudience() / 2;
    }
}
