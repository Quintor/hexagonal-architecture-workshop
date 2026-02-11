package nl.quintor.workshop.booking.domain.outbound;

import nl.quintor.workshop.customer.domain.model.Customer;

public interface CustomerServiceClient {
    GetOrCreateCustomerResponse GetOrCreateCustomer(GetOrCreateCustomerRequest getOrCreateCustomerRequest);
}
