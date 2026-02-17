package nl.quintor.workshop.customer.domain.port.inbound;

import jakarta.validation.Valid;

public interface CustomerApi {
    GetOrCreateCustomerReply getOrCreateCustomer(@Valid GetOrCreateCustomerCommand command);
}
