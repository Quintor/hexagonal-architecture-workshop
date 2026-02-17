package nl.quintor.workshop.customer.adapter.outbound.persistence;

import lombok.RequiredArgsConstructor;
import nl.quintor.workshop.customer.domain.model.Customer;
import nl.quintor.workshop.customer.domain.port.outbound.CustomerRepositorySpiPort;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryJpaAdapter implements CustomerRepositorySpiPort {
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
}


