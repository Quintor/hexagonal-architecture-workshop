package nl.quintor.workshop.customer.application.service;

import lombok.RequiredArgsConstructor;
import nl.quintor.workshop.customer.domain.model.Customer;
import nl.quintor.workshop.customer.domain.port.outbound.CustomerRepository;

@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public void validateOrCreateCustomer(Customer customer) {
        customerRepository.findById(String.valueOf(customer.getId()))
                .orElseGet(() -> customerRepository.save(customer));
    }
}
