package nl.quintor.workshop.booking.adapter.outbound.service;

import lombok.RequiredArgsConstructor;
import nl.quintor.workshop.booking.domain.port.outbound.CustomerServiceClient;
import nl.quintor.workshop.booking.domain.port.outbound.GetOrCreateCustomerRequest;
import nl.quintor.workshop.booking.domain.port.outbound.GetOrCreateCustomerResponse;
import nl.quintor.workshop.customer.domain.port.inbound.CustomerService;
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
