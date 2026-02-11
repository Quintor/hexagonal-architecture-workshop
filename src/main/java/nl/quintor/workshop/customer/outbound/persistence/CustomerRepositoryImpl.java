package nl.quintor.workshop.customer.outbound.persistence;

import lombok.RequiredArgsConstructor;
import nl.quintor.workshop.customer.domain.model.Customer;
import nl.quintor.workshop.customer.domain.outbound.CustomerRepository;

import java.util.Optional;

@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {
    private final CustomerEntityMapper customerEntityMapper;
    private final SpringDataCustomerRepository springDataCustomerRepository;

    @Override
    public boolean existsById(String customerId) {
        try {
            Long id = Long.parseLong(customerId);
            return springDataCustomerRepository.existsById(id);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public Optional<Customer> findById(String customerId) {
        try {
            Long id = Long.parseLong(customerId);
            return springDataCustomerRepository.findById(id)
                    .map(customerEntityMapper::toDomain);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    @Override
    public Customer save(Customer customer) {
        var customerEntity = customerEntityMapper.toEntity(customer);
        var savedEntity = springDataCustomerRepository.save(customerEntity);
        return customerEntityMapper.toDomain(savedEntity);
    }
}


