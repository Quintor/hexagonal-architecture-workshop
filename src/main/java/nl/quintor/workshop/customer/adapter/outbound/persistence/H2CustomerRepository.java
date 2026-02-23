package nl.quintor.workshop.customer.adapter.outbound.persistence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.quintor.workshop.customer.domain.model.Customer;
import nl.quintor.workshop.customer.domain.port.outbound.CustomerRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class H2CustomerRepository implements CustomerRepository {
    private final CustomerEntityMapper customerEntityMapper;
    private final SpringDataCustomerRepository springDataCustomerRepository;

    @Override
    public Optional<Customer> findByPhoneNumber(String phoneNumber) {
        log.info("[H2CustomerRepository] Zoeken naar customer met phoneNumber: {}", phoneNumber);
        var result = springDataCustomerRepository.findByPhoneNumber(phoneNumber)
                .map(customerEntityMapper::toDomain);
        if (result.isPresent()) {
            log.info("[H2CustomerRepository] Customer gevonden, ID: {}", result.get().getId());
        } else {
            log.info("[H2CustomerRepository] Geen customer gevonden voor phoneNumber: {}", phoneNumber);
        }
        return result;
    }

    @Override
    public Customer save(Customer customer) {
        log.info("[H2CustomerRepository] Opslaan van customer: {}", customer.getName());

        var customerEntity = customerEntityMapper.toEntity(customer);
        var savedEntity = springDataCustomerRepository.save(customerEntity);
        log.info("[H2CustomerRepository] Customer opgeslagen met ID: {}", savedEntity.getId());

        return customerEntityMapper.toDomain(savedEntity);
    }

    @Override
    public List<Customer> findAll() {
        log.info("[H2CustomerRepository] Ophalen van alle customers uit database");

        var customers = springDataCustomerRepository.findAll()
                .stream()
                .map(customerEntityMapper::toDomain)
                .toList();

        log.info("[H2CustomerRepository] Totaal customers opgehaald: {}", customers.size());
        return customers;
    }
}
