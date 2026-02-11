package nl.quintor.workshop.customer.domain.service;

import lombok.RequiredArgsConstructor;
import nl.quintor.workshop.customer.domain.inbound.CustomerDto;
import nl.quintor.workshop.customer.domain.inbound.CustomerService;

@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final nl.quintor.workshop.customer.application.service.CustomerService customerService;

    @Override
    public void validateOrCreateCustomer(CustomerDto customerDTO) {
        customerService.validateOrCreateCustomer(ServiceCustomerDtoMapper.INSTANCE.toDomain(customerDTO));
    }
}
