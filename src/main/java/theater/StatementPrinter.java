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

        for (Performance performance : invoice.getPerformances()) {

            final int thisAmount = getAmount(performance);

            // use helper for volume credits (Task 2.2)
            volumeCredits += getVolumeCredits(performance);

            // use usd(...) for money formatting (Task 2.3)
            result.append(String.format("  %s: %s (%s seats)%n",
                    getPlay(performance).getName(),
                    usd(thisAmount),
                    performance.getAudience()));

            totalAmount += thisAmount;
        }

        result.append(String.format("Amount owed is %s%n",
                usd(totalAmount)));
        result.append(String.format("You earned %s credits%n", volumeCredits));

        return result.toString();
    }

    // Helper to look up the play for a performance (Task 2.1).
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

    // Helper to compute the volume credits for ONE performance (Task 2.2).
    private int getVolumeCredits(Performance performance) {
        int result = 0;

        result += Math.max(
                performance.getAudience() - Constants.BASE_VOLUME_CREDIT_THRESHOLD, 0);

        if ("comedy".equals(getPlay(performance).getType())) {
            result += performance.getAudience()
                    / Constants.COMEDY_EXTRA_VOLUME_FACTOR;
        }

        return result;
    }

    // ★ New helper from Task 2.3: format an amount (in cents) as US dollars.
    private String usd(int amount) {
        return NumberFormat.getCurrencyInstance(Locale.US)
                .format(amount / (double) Constants.PERCENT_FACTOR);
    }
}
