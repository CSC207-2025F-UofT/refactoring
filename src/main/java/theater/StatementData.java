package theater;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents the data structure passed around for generating statements,
 * holding the invoice details and play information.
 */
public class StatementData {
    private final Invoice invoice;
    private final List<PerformanceData> performances;

    public StatementData(Invoice invoice, Map<String, Play> plays) {
        this.invoice = invoice;

        this.performances = new ArrayList<>();
        for (Performance performance : invoice.getPerformances()) {
            performances.add(new PerformanceData(performance, plays.get(performance.getPlayID())));
        }
    }

    public String getCustomer() {
        return invoice.getCustomer();
    }

    public List<PerformanceData> getPerformances() {
        return performances;
    }


    /**
     * Nothing.
     * @return nothing
     */
    public int totalAmount() {
        int result = 0;
        for (PerformanceData performanceData : performances) {
            result += performanceData.amountFor();
        }
        return result;
    }

    /**
     * Nothing.
     * @return nothing
     */
    public int volumeCredits() {
        int result = 0;
        for (PerformanceData performanceData : performances) {
            result += Math.max(performanceData.getAudience() - Constants.BASE_VOLUME_CREDIT_THRESHOLD, 0);
            // add extra credit for every five comedy attendees
            if ("comedy".equals(performanceData.getType())) {
                result += performanceData.getAudience() / Constants.COMEDY_EXTRA_VOLUME_FACTOR;
            }
        }
        return result;
    }

}
