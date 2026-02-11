package nl.quintor.workshop.booking.domain.outbound;

public interface CustomerServiceClient {
    GetOrCreateCustomerResponse GetOrCreateCustomer(GetOrCreateCustomerRequest getOrCreateCustomerRequest);
}
