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

    private int amountFor(Performance performance, Play play) {
        int thisAmount = 0;
        switch (play.getType()) {
            case "tragedy":
                thisAmount = Constants.TRAGEDY_BASE_AMOUNT;
                if (performance.getAudience() > Constants.TRAGEDY_AUDIENCE_THRESHOLD) {
                    thisAmount += Constants.TRAGEDY_EXTRA_AMOUNT
                            * (performance.getAudience() - Constants.TRAGEDY_AUDIENCE_THRESHOLD);
                }
                break;
            case "comedy":
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

    private int volumeCreditsFor(Performance performance, Play play) {
        int credits = Math.max(performance.getAudience() - Constants.BASE_VOLUME_CREDIT_THRESHOLD, 0);
        if ("comedy".equals(play.getType())) {
            credits += performance.getAudience() / Constants.COMEDY_EXTRA_VOLUME_FACTOR;
        }
        return credits;
    }

    private String lineFor(Performance performance, Play play, int thisAmount) {
        final NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);
        return String.format(
                "  %s: %s (%s seats)%n",
                play.getName(),
                frmt.format(thisAmount / Constants.CENTS_PER_DOLLAR),
                performance.getAudience()
        );
    }

    private String summary(int totalAmount, int volumeCredits) {
        final NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);
        return String.format("Amount owed is %s%n", frmt.format(totalAmount / Constants.CENTS_PER_DOLLAR))
                + String.format("You earned %s credits%n", volumeCredits);
    }

    /**
     * Returns a formatted statement of the invoice associated with this printer.
     * @return the formatted statement
     * @throws RuntimeException if one of the play types is not known
     */
    public String statement() {
        final StringBuilder result = new StringBuilder(header());
        int totalAmount = 0;
        int volumeCredits = 0;

        for (Performance performance : invoice.getPerformances()) {
            final Play play = plays.get(performance.getPlayID());
            final int thisAmount = amountFor(performance, play);
            volumeCredits += volumeCreditsFor(performance, play);

            result.append(lineFor(performance, play, thisAmount));
            totalAmount += thisAmount;
        }

        result.append(summary(totalAmount, volumeCredits));
        return result.toString();
    }
}
