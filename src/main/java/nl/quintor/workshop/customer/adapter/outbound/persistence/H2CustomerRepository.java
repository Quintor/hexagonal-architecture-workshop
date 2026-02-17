package nl.quintor.workshop.customer.adapter.outbound.persistence;

import lombok.RequiredArgsConstructor;
import nl.quintor.workshop.customer.domain.model.Customer;
import nl.quintor.workshop.customer.domain.port.outbound.CustomerRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class H2CustomerRepository implements CustomerRepository {
    private final CustomerEntityMapper customerEntityMapper;
    private final SpringDataCustomerRepository springDataCustomerRepository;

    @Override
    public Optional<Customer> findByPhoneNumber(String phoneNumber) {
        return springDataCustomerRepository.findByPhoneNumber(phoneNumber)
                .map(customerEntityMapper::toDomain);
    }

    @Override
    public Customer save(Customer customer) {
        var customerEntity = customerEntityMapper.toEntity(customer);
        var savedEntity = springDataCustomerRepository.save(customerEntity);
        return customerEntityMapper.toDomain(savedEntity);
    }

    @Override
    public List<Customer> findAll() {
        return springDataCustomerRepository.findAll()
                .stream()
                .map(customerEntityMapper::toDomain)
                .toList();
    }
}
