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

    /**
     * Create a new StatementPrinter that will print statements for the given
     * invoice and set of plays.
     *
     * @param invoice the invoice to print
     * @param plays   mapping from play ID to play information
     */
    public StatementPrinter(Invoice invoice, Map<String, Play> plays) {
        this.invoice = invoice;
        this.plays = plays;
    }

    /**
     * Returns a formatted statement of the invoice associated with this printer.
     *
     * @return the formatted statement
     */
    public String statement() {
        int totalAmount = 0;
        int volumeCredits = 0;

        final StringBuilder result = new StringBuilder(
                "Statement for " + invoice.getCustomer() + System.lineSeparator());
        final NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

        for (Performance performance : invoice.getPerformances()) {

            final int thisAmount = getAmount(performance);

            // ★ Task 2.2: use helper to compute credits for this performance
            volumeCredits += getVolumeCredits(performance);

            result.append(String.format("  %s: %s (%s seats)%n",
                    getPlay(performance).getName(),
                    frmt.format(thisAmount / (double) Constants.PERCENT_FACTOR),
                    performance.getAudience()));

            totalAmount += thisAmount;
        }

        result.append(String.format("Amount owed is %s%n",
                frmt.format(totalAmount / (double) Constants.PERCENT_FACTOR)));
        result.append(String.format("You earned %s credits%n", volumeCredits));

        return result.toString();
    }

    // Helper to look up the play for a performance (from Task 2.1).
    private Play getPlay(Performance performance) {
        return plays.get(performance.getPlayID());
    }

    // Helper to compute the amount for a performance (Task 2.1 final form).
    private int getAmount(Performance performance) {

        final Play play = getPlay(performance);

        int result = 0;

        switch (play.getType()) {
            case "tragedy":
                result = Constants.TRAGEDY_BASE_AMOUNT;
                if (performance.getAudience() > Constants.TRAGEDY_AUDIENCE_THRESHOLD) {
                    result += Constants.TRAGEDY_OVER_BASE_CAPACITY_PER_PERSON
                            * (performance.getAudience()
                            - Constants.TRAGEDY_AUDIENCE_THRESHOLD);
                }
                break;

            case "comedy":
                result = Constants.COMEDY_BASE_AMOUNT;
                if (performance.getAudience() > Constants.COMEDY_AUDIENCE_THRESHOLD) {
                    result += Constants.COMEDY_OVER_BASE_CAPACITY_AMOUNT
                            + Constants.COMEDY_OVER_BASE_CAPACITY_PER_PERSON
                            * (performance.getAudience()
                            - Constants.COMEDY_AUDIENCE_THRESHOLD);
                }
                result += Constants.COMEDY_AMOUNT_PER_AUDIENCE
                        * performance.getAudience();
                break;

            default:
                throw new RuntimeException(
                        String.format("unknown type: %s", play.getType()));
        }

        return result;
    }

    // ★ New helper from Task 2.2: compute volume credits for ONE performance.
    private int getVolumeCredits(Performance performance) {
        int result = 0;

        // base credits
        result += Math.max(
                performance.getAudience() - Constants.BASE_VOLUME_CREDIT_THRESHOLD, 0);

        // extra credits for comedies
        if ("comedy".equals(getPlay(performance).getType())) {
            result += performance.getAudience()
                    / Constants.COMEDY_EXTRA_VOLUME_FACTOR;
        }

        return result;
    }
}
