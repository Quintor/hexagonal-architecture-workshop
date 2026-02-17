package nl.quintor.workshop.customer.domain.port.outbound;

import nl.quintor.workshop.customer.domain.model.Customer;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    Optional<Customer> findByPhoneNumber(String phoneNumber);

    Customer save(Customer customer);

    List<Customer> findAll();
}
