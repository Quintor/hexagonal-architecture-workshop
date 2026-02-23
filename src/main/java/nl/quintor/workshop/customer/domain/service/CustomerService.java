package nl.quintor.workshop.customer.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.quintor.workshop.customer.domain.port.inbound.CustomerApi;
import nl.quintor.workshop.customer.domain.port.inbound.GetOrCreateCustomerCommand;
import nl.quintor.workshop.customer.domain.port.inbound.GetOrCreateCustomerReply;
import nl.quintor.workshop.customer.domain.model.Customer;
import nl.quintor.workshop.customer.domain.port.outbound.CustomerRepository;
import org.springframework.validation.annotation.Validated;
import java.util.List;

@Slf4j
@Validated
@RequiredArgsConstructor
public class CustomerService implements CustomerApi {
    private final CustomerRepository customerRepository;

    @Override
    public GetOrCreateCustomerReply getOrCreateCustomer(GetOrCreateCustomerCommand command) {
        log.info("[CustomerService] Zoeken naar klant met telefoon: {}", command.phoneNumber());
        var customer = customerRepository.findByPhoneNumber(command.phoneNumber())
                .orElseGet(() -> {
                    log.info("[CustomerService] Customer niet gevonden, nieuwe customer aanmaken: {}", command.name());
                    return customerRepository.save(Customer.builder()
                            .name(command.name())
                            .phoneNumber(command.phoneNumber())
                            .build());
                });

        log.info("[CustomerService] Customer verwerkt, ID: {}", customer.getId());
        return new GetOrCreateCustomerReply(customer.getId(), customer.getName(), customer.getPhoneNumber());
    }

    @Override
    public List<Customer> getAllCustomers() {
        log.info("[CustomerService] Ophalen van alle customers");
        var customers = customerRepository.findAll();
        log.info("[CustomerService] Totaal customers opgehaald: {}", customers.size());

        return customers;
    }
}
