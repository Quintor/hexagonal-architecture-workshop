package nl.quintor.workshop.customer.domain.outbound;

import nl.quintor.workshop.customer.domain.model.Customer;
import java.util.Optional;

public interface CustomerRepository {
    boolean existsById(String customerId);

    Optional<Customer> findById(String customerId);

    Customer save(Customer customer);
}
