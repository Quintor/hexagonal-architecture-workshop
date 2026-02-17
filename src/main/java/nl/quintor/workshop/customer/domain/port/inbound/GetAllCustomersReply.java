package nl.quintor.workshop.customer.domain.port.inbound;

import java.util.UUID;

public record GetAllCustomersReply(
        UUID id,
        String email,
        String phoneNumber) {
}
