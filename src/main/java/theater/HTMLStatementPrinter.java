package theater;

/**
 * Renders statements in HTML format.
 */
public class HTMLStatementPrinter extends StatementPrinter {

    public HTMLStatementPrinter(Invoice invoice, java.util.Map<String, Play> plays) {
        super(invoice, plays);
    }

    @Override
    public String statement() {
        final StatementData statementData = getStatementData();
        final StringBuilder result = new StringBuilder(String.format("<h1>Statement for %s</h1>%n",
                statementData.getCustomer()));
        result.append("<table>").append(System.lineSeparator());
        result.append(String.format(" <caption>Statement for %s</caption>%n", statementData.getCustomer()));
        result.append(" <tr><th>play</th><th>seats</th><th>cost</th></tr>").append(System.lineSeparator());
        for (PerformanceData perfData : statementData.getPerformances()) {
            result.append(String.format(" <tr><td>%s</td><td>%s</td><td>%s</td></tr>%n",
                    perfData.getName(),
                    perfData.getAudience(),
                    usd(perfData.getAmount())));
        }
        result.append("</table>").append(System.lineSeparator());

        result.append(String.format("<p>Amount owed is <em>%s</em></p>%n", usd(statementData.totalAmount())));
        result.append(String.format("<p>You earned <em>%s</em> credits</p>%n", statementData.volumeCredits()));
        return result.toString();
    }
}
