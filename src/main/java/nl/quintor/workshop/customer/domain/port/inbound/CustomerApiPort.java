package nl.quintor.workshop.customer.domain.port.inbound;

public interface CustomerApiPort {
    GetOrCreateCustomerReply getOrCreateCustomer(GetOrCreateCustomerCommand command);
}
