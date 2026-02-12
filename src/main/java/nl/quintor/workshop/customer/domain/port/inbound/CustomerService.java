package nl.quintor.workshop.customer.domain.port.inbound;

public interface CustomerService {
    GetOrCreateCustomerReply GetOrCreateCustomer(GetOrCreateCustomerCommand command);
}
