package nl.quintor.workshop.booking.domain.outbound;

public record GetOrCreateCustomerRequest(String email, String phoneNumber) {
}
