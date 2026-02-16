package nl.quintor.workshop.customer.domain.service;

import jakarta.transaction.Transactional;
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
    @Transactional
    public GetOrCreateCustomerReply getOrCreateCustomer(GetOrCreateCustomerCommand command) {
        System.out.println("Phone number: " + command.phoneNumber());
        var customer = customerRepositorySpiPort.findByPhoneNumber(command.phoneNumber())
                .orElseGet(() -> customerRepositorySpiPort.save(Customer.builder()
                        .phoneNumber(command.phoneNumber())
                        .build()));

        return new GetOrCreateCustomerReply(customer.getId());
    }
}
