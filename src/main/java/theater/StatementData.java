package theater;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Container for the data needed to render a statement.
 */
public class StatementData {
    private final Invoice invoice;
    private final Map<String, Play> plays;
    private final List<PerformanceData> performances = new ArrayList<>();

    /**
     * Builds statement data for a given invoice and play lookup.
     *
     * @param invoice invoice being rendered
     * @param plays map of play id to play details
     */
    public StatementData(Invoice invoice, Map<String, Play> plays) {
        this.invoice = invoice;
        this.plays = plays;
        for (Performance performance : invoice.getPerformances()) {
            performances.add(createPerformanceData(performance));
        }
    }

    /**
     * Customer name associated with the invoice.
     *
     * @return customer identifier
     */
    public String getCustomer() {
        return invoice.getCustomer();
    }

    /**
     * Computed performance data for the invoice.
     *
     * @return list of performance data entries
     */
    public List<PerformanceData> getPerformances() {
        return performances;
    }

    /**
     * Calculates the total amount owed for all performances.
     *
     * @return total amount in cents
     */
    public int totalAmount() {
        int result = 0;
        for (PerformanceData performanceData : performances) {
            result += performanceData.getAmount();
        }
        return result;
    }

    /**
     * Calculates total volume credits for all performances.
     *
     * @return total volume credits
     */
    public int volumeCredits() {
        int result = 0;
        for (PerformanceData performanceData : performances) {
            result += performanceData.getVolumeCredits();
        }
        return result;
    }

    private Play getPlay(Performance performance) {
        return plays.get(performance.getPlayID());
    }

    private PerformanceData createPerformanceData(Performance performance) {
        final AbstractPerformanceCalculator calculator = AbstractPerformanceCalculator
                .createPerformanceCalculator(performance, getPlay(performance));
        return new PerformanceData(performance, getPlay(performance), calculator);
    }
}
