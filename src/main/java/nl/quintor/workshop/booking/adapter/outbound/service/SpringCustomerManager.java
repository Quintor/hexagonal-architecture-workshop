package nl.quintor.workshop.booking.adapter.outbound.service;

import lombok.RequiredArgsConstructor;
import nl.quintor.workshop.booking.domain.port.outbound.CustomerManager;
import nl.quintor.workshop.booking.domain.port.outbound.GetOrCreateCustomerRequest;
import nl.quintor.workshop.booking.domain.port.outbound.GetOrCreateCustomerResponse;
import nl.quintor.workshop.customer.domain.port.inbound.CustomerApi;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpringCustomerManager implements CustomerManager {
    private final CustomerApi customerApi;
    private final CustomerManagerMapper customerManagerMapper;

    @Override
    public GetOrCreateCustomerResponse getOrCreateCustomer(GetOrCreateCustomerRequest getOrCreateCustomerRequest) {
        var command = customerManagerMapper.toCommand(getOrCreateCustomerRequest);
        var reply = customerApi.getOrCreateCustomer(command);

        return customerManagerMapper.fromReply(reply);
    }
}
