package nl.quintor.workshop.booking.outbound.service;

import lombok.RequiredArgsConstructor;
import nl.quintor.workshop.booking.domain.outbound.CustomerServiceClient;
import nl.quintor.workshop.booking.domain.outbound.GetOrCreateCustomerRequest;
import nl.quintor.workshop.booking.domain.outbound.GetOrCreateCustomerResponse;
import nl.quintor.workshop.customer.domain.inbound.CustomerService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerInternalServiceAdapter implements CustomerServiceClient {
    private final CustomerService customerService;
    private final CustomerInternalMapper customerInternalMapper;

    @Override
    public GetOrCreateCustomerResponse GetOrCreateCustomer(GetOrCreateCustomerRequest getOrCreateCustomerRequest) {
        var command = customerInternalMapper.toCommand(getOrCreateCustomerRequest);
        var reply = customerService.GetOrCreateCustomer(command);

        return customerInternalMapper.fromReply(reply);
    }
}
