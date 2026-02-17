package nl.quintor.workshop.booking.adapter.outbound.service;

import lombok.RequiredArgsConstructor;
import nl.quintor.workshop.booking.domain.port.outbound.CustomerServiceClient;
import nl.quintor.workshop.booking.domain.port.outbound.GetOrCreateCustomerRequest;
import nl.quintor.workshop.booking.domain.port.outbound.GetOrCreateCustomerResponse;
import nl.quintor.workshop.customer.domain.port.inbound.CustomerApiPort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerInternalServiceAdapter implements CustomerServiceClient {
    private final CustomerApiPort customerApiPort;
    private final CustomerInternalMapper customerInternalMapper;

    @Override
    public GetOrCreateCustomerResponse getOrCreateCustomer(GetOrCreateCustomerRequest getOrCreateCustomerRequest) {
        var command = customerInternalMapper.toCommand(getOrCreateCustomerRequest);
        var reply = customerApiPort.getOrCreateCustomer(command);

        return customerInternalMapper.fromReply(reply);
    }
}
