package nl.quintor.workshop.booking.domain.port.outbound;

public interface CustomerServiceClient {
    GetOrCreateCustomerResponse GetOrCreateCustomer(GetOrCreateCustomerRequest getOrCreateCustomerRequest);
}
