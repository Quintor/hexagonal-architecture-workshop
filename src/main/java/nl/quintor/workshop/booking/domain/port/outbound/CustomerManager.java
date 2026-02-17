package nl.quintor.workshop.booking.domain.port.outbound;

public interface CustomerManager {
    GetOrCreateCustomerResponse getOrCreateCustomer(GetOrCreateCustomerRequest getOrCreateCustomerRequest);
}
