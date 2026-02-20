package nl.quintor.workshop.customer.domain.port.inbound;

import java.util.UUID;

public record GetOrCreateCustomerReply(UUID customerId, String phoneNumber) {
}
