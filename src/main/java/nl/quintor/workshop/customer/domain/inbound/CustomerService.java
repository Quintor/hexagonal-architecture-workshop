package nl.quintor.workshop.customer.domain.inbound;

public interface CustomerService {
    void validateOrCreateCustomer(CustomerDto customerDTO);
}
