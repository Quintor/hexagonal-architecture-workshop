package nl.quintor.workshop.customer.domain.service;

import lombok.RequiredArgsConstructor;
import nl.quintor.workshop.customer.domain.port.inbound.CustomerApiPort;
import nl.quintor.workshop.customer.domain.port.inbound.GetOrCreateCustomerCommand;
import nl.quintor.workshop.customer.domain.port.inbound.GetOrCreateCustomerReply;
import nl.quintor.workshop.customer.domain.model.Customer;
import nl.quintor.workshop.customer.domain.port.outbound.CustomerRepositorySpiPort;

@RequiredArgsConstructor
public class CustomerApiService implements CustomerApiPort {
    private final CustomerRepositorySpiPort customerRepositorySpiPort;

    @Override
    public GetOrCreateCustomerReply getOrCreateCustomer(GetOrCreateCustomerCommand command) {
        System.out.println("Got email: " + command.email());
        var customer = customerRepositorySpiPort.findByEmail(command.email())
                .orElseGet(() -> customerRepositorySpiPort.save(Customer.builder()
                        .phoneNumber(command.phoneNumber())
                        .email(command.email())
                        .build()));

        return new GetOrCreateCustomerReply(customer.getId());
    }
}
