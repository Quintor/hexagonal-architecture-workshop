package nl.quintor.workshop.booking.domain.port.outbound;

import java.util.UUID;

public record GetOrCreateCustomerResponse(UUID customerId) {
}
