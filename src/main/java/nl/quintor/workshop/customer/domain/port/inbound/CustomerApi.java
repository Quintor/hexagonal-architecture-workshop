package nl.quintor.workshop.customer.domain.port.inbound;

import jakarta.validation.Valid;
import nl.quintor.workshop.customer.domain.model.Customer;
import java.util.List;

public interface CustomerApi {
    GetOrCreateCustomerReply getOrCreateCustomer(@Valid GetOrCreateCustomerCommand command);

    List<Customer> getAllCustomers();
}
