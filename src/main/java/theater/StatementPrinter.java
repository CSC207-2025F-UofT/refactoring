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

    public StatementPrinter(Invoice invoice, Map<String, Play> plays) {
        this.invoice = invoice;
        this.plays = plays;
    }

    private String header() {
        return "Statement for " + invoice.getCustomer() + System.lineSeparator();
    }

    private int amountFor(final Performance performance, final Play play) {
        int thisAmount = 0;
        switch (play.getType()) {
            case Constants.PLAY_TYPE_TRAGEDY:
                thisAmount = Constants.TRAGEDY_BASE_AMOUNT;
                if (performance.getAudience() > Constants.TRAGEDY_AUDIENCE_THRESHOLD) {
                    thisAmount += Constants.TRAGEDY_EXTRA_AMOUNT
                            * (performance.getAudience() - Constants.TRAGEDY_AUDIENCE_THRESHOLD);
                }
                break;
            case Constants.PLAY_TYPE_COMEDY:
                thisAmount = Constants.COMEDY_BASE_AMOUNT;
                if (performance.getAudience() > Constants.COMEDY_AUDIENCE_THRESHOLD) {
                    thisAmount += Constants.COMEDY_OVER_BASE_CAPACITY_AMOUNT
                            + (Constants.COMEDY_OVER_BASE_CAPACITY_PER_PERSON
                            * (performance.getAudience() - Constants.COMEDY_AUDIENCE_THRESHOLD));
                }
                thisAmount += Constants.COMEDY_AMOUNT_PER_AUDIENCE * performance.getAudience();
                break;
            default:
                throw new RuntimeException(
                        String.format("unknown type: %s", play.getType()));
        }
        return thisAmount;
    }

    private int getVolumeCredits(final Performance performance, final Play play) {
        int result = 0;
        result += Math.max(performance.getAudience() - Constants.BASE_VOLUME_CREDIT_THRESHOLD, 0);
        if (Constants.PLAY_TYPE_COMEDY.equals(play.getType())) {
            result += performance.getAudience() / Constants.COMEDY_EXTRA_VOLUME_FACTOR;
        }
        return result;
    }

    private String lineFor(final Performance performance, final Play play, final int thisAmount) {
        final NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);
        return String.format(
                "  %s: %s (%s seats)%n",
                play.getName(),
                frmt.format(thisAmount / Constants.CENTS_PER_DOLLAR),
                performance.getAudience()
        );
    }

    private String summary(final int totalAmount, final int volumeCredits) {
        final NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);
        return String.format("Amount owed is %s%n", frmt.format(totalAmount / Constants.CENTS_PER_DOLLAR))
                + String.format("You earned %s credits%n", volumeCredits);
    }

    private int getTotalVolumeCredits() {
        int result = 0;
        for (final Performance performance : invoice.getPerformances()) {
            final Play play = plays.get(performance.getPlayID());
            final PerformanceCalculator calculator = new PerformanceCalculator(performance, play);
            result += calculator.getVolumeCredits();
        }
        return result;
    }

    private int getTotalAmount() {
        int result = 0;
        for (final Performance performance : invoice.getPerformances()) {
            final Play play = plays.get(performance.getPlayID());
            final PerformanceCalculator calculator = new PerformanceCalculator(performance, play);
            result += calculator.getAmount();
        }
        return result;
    }

    /**
     * Returns a formatted statement of the invoice associated with this printer.
     * @return the formatted statement
     * @throws RuntimeException if one of the play types is not known
     */
    public String statement() {
        final StringBuilder result = new StringBuilder(header());

        final int totalAmount = getTotalAmount();

        final int volumeCredits = getTotalVolumeCredits();

        for (final Performance performance : invoice.getPerformances()) {
            final Play play = plays.get(performance.getPlayID());
            final PerformanceCalculator calculator = new PerformanceCalculator(performance, play);
            final int thisAmount = calculator.getAmount();
            result.append(lineFor(performance, play, thisAmount));
        }

        result.append(summary(totalAmount, volumeCredits));
        return result.toString();
    }
}
