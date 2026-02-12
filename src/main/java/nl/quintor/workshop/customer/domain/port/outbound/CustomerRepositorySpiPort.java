package nl.quintor.workshop.customer.domain.port.outbound;

import nl.quintor.workshop.customer.domain.model.Customer;
import java.util.Optional;

public interface CustomerRepositorySpiPort {
    Optional<Customer> findByEmail(String email);

    Customer save(Customer customer);
}
