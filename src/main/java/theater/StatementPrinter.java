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
     * Construct a new StatementPrinter for the given invoice and plays.
     *
     * @param invoice the invoice to print
     * @param plays   the mapping from play identifiers to play information
     */
    public StatementPrinter(Invoice invoice, Map<String, Play> plays) {
        this.invoice = invoice;
        this.plays = plays;
    }

    /**
     * Returns a formatted statement of the invoice associated with this printer.
     *
     * @return the formatted statement
     * @throws RuntimeException if one of the play types is not known
     */
    public String statement() {
        int totalAmount = 0;
        int volumeCredits = 0;
        final StringBuilder result =
                new StringBuilder("Statement for " + invoice.getCustomer()
                        + System.lineSeparator());

        final NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

        for (Performance p : invoice.getPerformances()) {

            // add volume credits
            switch (getPlay(p).getType()) {
                case "tragedy":
                    volumeCredits += Math.max(p.getAudience()
                            - Constants.BASE_VOLUME_CREDIT_THRESHOLD, 0);
                    break;

                case "comedy":
                    volumeCredits += Math.max(p.getAudience()
                            - Constants.BASE_VOLUME_CREDIT_THRESHOLD, 0);
                    volumeCredits += p.getAudience()
                            / Constants.COMEDY_EXTRA_VOLUME_FACTOR;
                    break;

                case "history":
                    volumeCredits += Math.max(p.getAudience()
                            - Constants.HISTORY_VOLUME_CREDIT_THRESHOLD, 0);
                    break;

                case "pastoral":
                    volumeCredits += Math.max(p.getAudience()
                            - Constants.PASTORAL_VOLUME_CREDIT_THRESHOLD, 0);
                    volumeCredits += p.getAudience() / 2;
                    break;

                default:
                    throw new RuntimeException(String.format("unknown type: %s",
                            getPlay(p).getType()));
            }

            // print line for this order
            result.append(String.format("  %s: %s (%s seats)%n",
                    getPlay(p).getName(),
                    frmt.format(getAmount(p) / Constants.PERCENT_FACTOR),
                    p.getAudience()));
            totalAmount += getAmount(p);
        }
        result.append(String.format("Amount owed is %s%n",
                frmt.format(totalAmount / Constants.PERCENT_FACTOR)));
        result.append(String.format("You earned %s credits%n", volumeCredits));
        return result.toString();
    }

    private int getAmount(Performance performance) {
        int result;
        switch (getPlay(performance).getType()) {
            case "tragedy":
                result = Constants.TRAGEDY_BASE_AMOUNT;
                if (performance.getAudience()
                        > Constants.TRAGEDY_AUDIENCE_THRESHOLD) {
                    result +=
                            Constants
                                    .TRAGEDY_OVER_BASE_CAPACITY_PER_PERSON
                            * (performance.getAudience()
                                    - Constants.TRAGEDY_AUDIENCE_THRESHOLD);
                }
                break;
            case "comedy":
                result = Constants.COMEDY_BASE_AMOUNT;
                if (performance.getAudience() > Constants.COMEDY_AUDIENCE_THRESHOLD) {
                    result += Constants.COMEDY_OVER_BASE_CAPACITY_AMOUNT
                            + (Constants
                            .COMEDY_OVER_BASE_CAPACITY_PER_PERSON
                            * (performance.getAudience()
                            - Constants.COMEDY_AUDIENCE_THRESHOLD));
                }
                result += Constants.COMEDY_AMOUNT_PER_AUDIENCE
                        * performance.getAudience();
                break;
            case "history":
                result = Constants.HISTORY_BASE_AMOUNT;
                if (performance.getAudience() > Constants
                        .HISTORY_AUDIENCE_THRESHOLD) {
                    result += Constants
                            .HISTORY_OVER_BASE_CAPACITY_PER_PERSON
                            * (performance.getAudience()
                            - Constants.HISTORY_AUDIENCE_THRESHOLD);
                }
                break;

            case "pastoral":
                result = Constants.PASTORAL_BASE_AMOUNT;
                if (performance.getAudience() > Constants
                        .PASTORAL_AUDIENCE_THRESHOLD) {
                    result += Constants
                            .PASTORAL_OVER_BASE_CAPACITY_PER_PERSON
                            * (performance.getAudience()
                            - Constants.PASTORAL_AUDIENCE_THRESHOLD);
                }
                break;
            default:
                throw new RuntimeException(String.format("unknown type: %s",
                        getPlay(performance).getType()));
        }
        return result;
    }

    private Play getPlay(Performance performance) {
        return plays.get(performance.getPlayID());
    }
}
