package nl.quintor.workshop.customer.domain.inbound;

public record GetOrCreateCustomerCommand(String email, String phoneNumber) {
}
