package nl.quintor.workshop.customer.domain.inbound;

public interface CustomerService {
    GetOrCreateCustomerReply GetOrCreateCustomer(GetOrCreateCustomerCommand command);
}
