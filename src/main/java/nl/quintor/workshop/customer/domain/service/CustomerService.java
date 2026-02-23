package nl.quintor.workshop.customer.domain.service;

import lombok.RequiredArgsConstructor;
import nl.quintor.workshop.customer.domain.port.inbound.CustomerApi;
import nl.quintor.workshop.customer.domain.port.inbound.GetOrCreateCustomerCommand;
import nl.quintor.workshop.customer.domain.port.inbound.GetOrCreateCustomerReply;
import nl.quintor.workshop.customer.domain.model.Customer;
import nl.quintor.workshop.customer.domain.port.outbound.CustomerRepository;
import org.springframework.validation.annotation.Validated;
import java.util.List;

@Validated
@RequiredArgsConstructor
public class CustomerService implements CustomerApi {
    private final CustomerRepository customerRepository;

    @Override
    public GetOrCreateCustomerReply getOrCreateCustomer(GetOrCreateCustomerCommand command) {
        var customer = customerRepository.findByPhoneNumber(command.phoneNumber())
                .orElseGet(() -> customerRepository.save(Customer.builder()
                        .name(command.name())
                        .phoneNumber(command.phoneNumber())
                        .build()));

        return new GetOrCreateCustomerReply(customer.getId(), customer.getName(), customer.getPhoneNumber());
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}
