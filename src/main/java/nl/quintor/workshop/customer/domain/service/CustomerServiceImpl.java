package nl.quintor.workshop.customer.domain.service;

import lombok.RequiredArgsConstructor;
import nl.quintor.workshop.customer.domain.inbound.CustomerDto;
import nl.quintor.workshop.customer.domain.inbound.CustomerService;
import nl.quintor.workshop.customer.domain.outbound.CustomerRepository;

@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;


    @Override
    public void validateOrCreateCustomer(CustomerDto customerDTO) {
        var customer = ServiceCustomerDtoMapper.INSTANCE.toDomain(customerDTO);
        customerRepository.findById(String.valueOf(customer.getId()))
                .orElseGet(() -> customerRepository.save(customer));
    }
}
