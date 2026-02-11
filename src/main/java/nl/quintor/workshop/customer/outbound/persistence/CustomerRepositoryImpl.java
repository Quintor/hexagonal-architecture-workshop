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
    public Optional<Customer> findByEmail(String email) {
        return springDataCustomerRepository.findByEmail(email)
                .map(customerEntityMapper::toDomain);
    }

    @Override
    public Customer save(Customer customer) {
        var customerEntity = customerEntityMapper.toEntity(customer);
        var savedEntity = springDataCustomerRepository.save(customerEntity);
        return customerEntityMapper.toDomain(savedEntity);
    }
}


