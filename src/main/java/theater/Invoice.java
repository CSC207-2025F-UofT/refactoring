package theater;

import java.util.List;
import java.util.ArrayList;

/**
 * Class representing an invoice for a customer.
 */
public final class Invoice {

    private final String customer;
    private final List<Performance> performances;

    /**
     * Constructs an invoice with customer and performances.
     *
     * @param customer the customer name
     * @param performances the list of performances
     */
    public Invoice(final String customer, final List<Performance> performances) {
        this.customer = customer;
        this.performances = new ArrayList<>(performances);
    }

    /**
     * Returns the customer name.
     *
     * @return the customer name
     */
    public String getCustomer() {
        return customer;
    }

    /**
     * Returns a copy of the performances list.
     *
     * @return the list of performances
     */
    public List<Performance> getPerformances() {
        return new ArrayList<>(performances);
    }
}