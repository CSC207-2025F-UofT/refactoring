package theater;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

/**
 * This class generates a statement for a given invoice of performances.
 */
public class StatementPrinter {
    private final Invoice invoice;
    private final Map<String, Play> plays;
    private final StatementData statementData;

    public StatementPrinter(Invoice invoice, Map<String, Play> plays) {
        this.invoice = invoice;
        this.plays = plays;
        this.statementData = new StatementData(invoice, plays);
    }

    /**
     * Returns a formatted statement of the invoice associated with this printer.
     * @return the formatted statement
     * @throws RuntimeException if one of the play types is not known
     */
    public String statement() {
        return renderPlainText(statementData);
    }

    protected String renderPlainText(StatementData data) {
        final StringBuilder result =
                new StringBuilder("Statement for " + data.getCustomer() + System.lineSeparator());

        for (PerformanceData performanceData : data.getPerformances()) {
            result.append(String.format("  %s: %s (%s seats)%n",
                    performanceData.getName(), usd(performanceData.getAmount()),
                    performanceData.getAudience()));
        }

        result.append(String.format("Amount owed is %s%n",
                usd(data.totalAmount())));
        result.append(String.format("You earned %s credits%n", data.volumeCredits()));
        return result.toString();
    }

    protected String usd(int amount) {
        final NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
        return format.format(amount / (double) Constants.PERCENT_FACTOR);
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public Map<String, Play> getPlays() {
        return plays;
    }

    protected StatementData getStatementData() {
        return statementData;
    }

    protected Play getPlay(Performance performance) {
        return plays.get(performance.getPlayID());
    }

    protected int getAmount(Performance performance) {
        final AbstractPerformanceCalculator calculator =
                AbstractPerformanceCalculator.createPerformanceCalculator(performance, getPlay(performance));
        return calculator.getAmount();
    }

    protected int getVolumeCredits(Performance performance) {
        final AbstractPerformanceCalculator calculator =
                AbstractPerformanceCalculator.createPerformanceCalculator(performance, getPlay(performance));
        return calculator.getVolumeCredits();
    }

    protected int getTotalAmount() {
        return statementData.totalAmount();
    }

    protected int getTotalVolumeCredits() {
        return statementData.volumeCredits();
    }
}
