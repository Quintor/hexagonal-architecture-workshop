package nl.quintor.workshop.customer.infrastructure.inbound.services;

import lombok.RequiredArgsConstructor;
import nl.quintor.workshop.customer.api.CustomerDto;
import nl.quintor.workshop.customer.api.CustomerPublicService;
import nl.quintor.workshop.customer.application.service.CustomerService;

@RequiredArgsConstructor
public class CustomerPublicServiceImpl implements CustomerPublicService {
    private final CustomerService customerService;

    @Override
    public void validateOrCreateCustomer(CustomerDto customerDTO) {
        customerService.validateOrCreateCustomer(ServiceCustomerDtoMapper.INSTANCE.toDomain(customerDTO));
    }
}
