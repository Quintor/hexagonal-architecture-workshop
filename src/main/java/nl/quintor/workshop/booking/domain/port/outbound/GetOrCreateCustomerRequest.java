package nl.quintor.workshop.booking.domain.port.outbound;

public record GetOrCreateCustomerRequest(
        String name,
        String phoneNumber) {
}
