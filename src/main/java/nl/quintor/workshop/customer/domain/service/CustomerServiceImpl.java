package nl.quintor.workshop.customer.domain.service;

import lombok.RequiredArgsConstructor;
import nl.quintor.workshop.customer.domain.port.inbound.CustomerService;
import nl.quintor.workshop.customer.domain.port.inbound.GetOrCreateCustomerCommand;
import nl.quintor.workshop.customer.domain.port.inbound.GetOrCreateCustomerReply;
import nl.quintor.workshop.customer.domain.model.Customer;
import nl.quintor.workshop.customer.domain.port.outbound.CustomerRepository;

@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public GetOrCreateCustomerReply GetOrCreateCustomer(GetOrCreateCustomerCommand command) {
        System.out.println("Got email: " + command.email());
        var customer = customerRepository.findByEmail(command.email())
                .orElseGet(() -> customerRepository.save(Customer.builder()
                        .phoneNumber(command.phoneNumber())
                        .email(command.email())
                        .build()));

        return new GetOrCreateCustomerReply(customer.getId());
    }
}
