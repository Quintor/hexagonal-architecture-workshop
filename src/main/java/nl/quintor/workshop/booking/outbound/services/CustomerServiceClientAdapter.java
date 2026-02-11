package nl.quintor.workshop.booking.outbound.services;

import lombok.RequiredArgsConstructor;
import nl.quintor.workshop.booking.domain.model.Customer;
import nl.quintor.workshop.booking.domain.outbound.CustomerServiceClient;
import nl.quintor.workshop.customer.domain.inbound.CustomerDto;
import nl.quintor.workshop.customer.domain.inbound.CustomerService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerServiceClientAdapter implements CustomerServiceClient {
    private final CustomerService customerPublicService;
    private final ExternalCustomerDtoMapper externalCustomerDtoMapper;

    @Override
    public void validateOrCreateCustomer(Customer customer) {
        CustomerDto customerDto = externalCustomerDtoMapper.toDTO(customer);
        customerPublicService.validateOrCreateCustomer(customerDto);
    }
}
