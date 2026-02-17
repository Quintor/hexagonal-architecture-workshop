package nl.quintor.workshop.customer.domain.port.inbound;

public interface CustomerApi {
    GetOrCreateCustomerReply getOrCreateCustomer(GetOrCreateCustomerCommand command);
}
