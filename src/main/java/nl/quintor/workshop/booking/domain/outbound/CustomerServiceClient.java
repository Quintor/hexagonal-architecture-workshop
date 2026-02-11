package nl.quintor.workshop.booking.domain.outbound;

import nl.quintor.workshop.booking.domain.model.Customer;

public interface CustomerServiceClient {
    void validateOrCreateCustomer(Customer customer);
}
